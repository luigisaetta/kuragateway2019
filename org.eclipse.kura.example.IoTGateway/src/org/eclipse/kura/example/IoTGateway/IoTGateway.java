package org.eclipse.kura.example.IoTGateway;

import java.util.*;
import java.util.Map.Entry;

import org.eclipse.kura.configuration.ConfigurableComponent;
import org.eclipse.kura.data.DataService;
import org.eclipse.kura.data.listener.DataServiceListener;
import org.osgi.service.component.ComponentContext;
import org.slf4j.*;
import org.eclipse.kura.gpio.*;
import org.eclipse.kura.crypto.CryptoService;

public class IoTGateway implements DataServiceListener, ConfigurableComponent
{
	private static final Logger s_logger = LoggerFactory.getLogger(IoTGateway.class);

	private static final String APP_ID = "org.eclipse.kura.example.IoTGateway";

	/*
	 * Parameters configurable from UI
	 */
	// here I have access to the Configurable properties
	private Map<String, Object> properties;

	// MQTT
	private DataService _dataService;
	// GPIO
	private GPIOService _gpioService = null;

	// CryptoService
	private CryptoService _cryptoService;

	MessageParser parser = null;

	OracleIoTBaseClient iotClient = null;

	// to control attached LED (if RPI)
	private MyLED led = null;

	// to handle lazy activation of the gateway
	private static boolean isOracleClientActivated = false;

	public IoTGateway()
	{
	}

	protected void setDataService(DataService dataService)
	{
		debug("Bundle " + APP_ID + " setDataServiceCalled!");

		_dataService = dataService;

		_dataService.addDataServiceListener(this);
	}

	protected void unsetDataService(DataService dataService)
	{
		// to avoid multiple message effect
		_dataService.removeDataServiceListener(this);

		_dataService = null;
	}

	protected void setGPIOService(GPIOService gpioService)
	{
		info("Bundle " + APP_ID + " setGPIOServiceCalled!");

		_gpioService = gpioService;
	}

	protected void unsetGPIOService(GPIOService gpioService)
	{
		_gpioService = null;
	}

	protected void setCryptoService(final CryptoService cryptoService)
	{
		this._cryptoService = cryptoService;
	}

	protected void unsetCryptoService(final CryptoService cryptoService)
	{
		this._cryptoService = null;
	}

	/*
	 * new method for configuration
	 */
	protected void activate(ComponentContext componentContext, Map<String, Object> properties)
	{
		info("Bundle " + APP_ID + " has started with config!");

		// save the config
		this.properties = properties;

		// subscribe to TOPIC
		String TOPIC = (String) getProperty("msg.topic");

		subscribe(TOPIC);

		// define OUTPUT PIN
		if ((boolean) getProperty("blink.enable"))
		{
			activateLED((Integer) getProperty("pin.num"));
		}

		// define MsgParser
		String MSG_TYPE = (String) getProperty("msg.type");
		parser = MessageParserFactory.createParser(MSG_TYPE);

		if ((boolean) getProperty("mode.test") == false)
		{
			// NOT in TEST MODE.. will really send to IoT CS

			// introduced a factory to support polymorphism for send()
			// create a different client depending from MSG_TYPE
			String TA_PATHNAME = (String) getProperty("ta.pathname");

			// must decrypt the password using CryptoService
			String TA_STORE_PWD = decrypt((String) getProperty("ta.pwd"));

			info("ta_store_pwd: " + TA_STORE_PWD);

			iotClient = IoTClientFactory.createIoTClient(MSG_TYPE, TA_PATHNAME, TA_STORE_PWD);

			// reset to force activation
			isOracleClientActivated = false;
		}
	}

	protected void deactivate(ComponentContext componentContext)
	{
		debug("Bundle " + APP_ID + " has stopped!");

		// here the code that disactivate bundle processing
		String TOPIC = (String) getProperty("msg.topic");

		unSubscribe(TOPIC);

		iotClient = null;

		// reset to force activation
		isOracleClientActivated = false;
	}

	/*
	 * 
	 * handle change of configuration
	 * 
	 */
	public void updated(Map<String, Object> properties)
	{
		// verify if TOPIC has changed
		if (hasPropertyChanged(properties, "msg.topic"))
		{
			info("TOPIC changed!");

			handleMsgTopicChanged(properties);
		}

		// change msg.type
		if (hasPropertyChanged(properties, "msg.type"))
		{
			// MSG_TYPE changed
			info("MSG_TYPE changed!");

			handleMsgTypeChanged(properties);
		}

		// changed ta pathname
		if (hasPropertyChanged(properties, "ta.pathname"))
		{
			// TA pathname changed
			info("TA pathname changed!");
		}

		// blink.enable
		if (hasPropertyChanged(properties, "blink.enable"))
		{
			// BLINK_ENABLE changed
			info("BLINK_ENABLE changed!");

			handleBlinkEnableChanged(properties);
		}

		if (hasPropertyChanged(properties, "pin.num"))
		{
			info("PIN NUM changed!");

			handlePinNumChanged(properties);
		}

		// changed mode.test
		if (hasPropertyChanged(properties, "mode.test"))
		{
			// TEST MODE changed
			info("TEST MODE changed! ");

			handleTestModeChanged(properties);

		}

		// save the new configuration
		this.properties = properties;

		dumpProperties();
	}

	private void handleTestModeChanged(Map<String, Object> properties)
	{
		boolean newTestMode = (boolean) properties.get("mode.test");

		if (newTestMode == false) // not test mode
		{
			// introduced a factory to support polymorphism for send()
			// create a different client depending from MSG_TYPE
			String MSG_TYPE = (String) properties.get("msg.type");
			String TA_PATHNAME = (String) properties.get("ta.pathname");

			// must decrypt the password using CryptoService
			String TA_STORE_PWD = decrypt((String) properties.get("ta.pwd"));

			iotClient = IoTClientFactory.createIoTClient(MSG_TYPE, TA_PATHNAME, TA_STORE_PWD);

			// reset to force activation
			isOracleClientActivated = false;
		}
	}

	private void handlePinNumChanged(Map<String, Object> properties)
	{
		Integer PIN_NUM = (Integer) properties.get("pin.num");

		activateLED(PIN_NUM);
	}

	private void handleBlinkEnableChanged(Map<String, Object> properties)
	{
		Integer PIN_NUM = (Integer) properties.get("pin.num");

		activateLED(PIN_NUM);
	}

	private void handleMsgTypeChanged(Map<String, Object> properties)
	{
		parser = MessageParserFactory.createParser((String) properties.get("msg.type"));

		// should check if to create a new IoTClient
		// should solve the bug when you change MSG_TYPE at runtime
		handleTestModeChanged(properties);
	}

	private void handleMsgTopicChanged(Map<String, Object> properties)
	{
		// topic changed
		String newMsgTopic = (String) properties.get("msg.topic");
		String oldMsgTopic = (String) getProperty("msg.topic");

		// unsubscribe from old topic
		unSubscribe(oldMsgTopic);

		// subscribe to new
		subscribe(newMsgTopic);
	}

	private void activateLED(Integer PIN_NUM)
	{
		led = new MyLED(PIN_NUM.intValue());

		led.setGPIOService(_gpioService);

		led.activatePin();
	}

	@Override
	public void onConnectionEstablished()
	{
		info("Connection established");

		String TOPIC = (String) getProperty("msg.topic");

		subscribe(TOPIC);
	}

	@Override
	public void onConnectionLost(Throwable arg0)
	{
		info("Connection lost");

	}

	@Override
	public void onDisconnected()
	{

	}

	@Override
	public void onDisconnecting()
	{

	}
	
	/**
	 * 
	 * onMessageArrived
	 * 
	 */
	@Override
	public void onMessageArrived(String topic, byte[] payload, int qos, boolean retained)
	{
		info(" Message arrived on topic " + topic);

		// is on the subscribed topic?
		String SUBSCRIBED_TOPIC = (String) (getProperty("msg.topic"));

		//
		// need to extend to support wildcard in TOPIC definition
		//
		if (areCompatibleTopics(topic, SUBSCRIBED_TOPIC))
		{
			// topic OK, proceed!

			// if NOT test mode... LAZY ACTIVATION of IoT Client (when first needed)
			if ((!isTestMode()) && !isOracleClientActivated)
			{
				try
				{
					iotClient.activateOracleGateway();

					isOracleClientActivated = true;
				} catch (Exception e)
				{
					error("in onMessageArrived...");

					e.printStackTrace();
				}
			}

			// blink LED on GPIO PIN_NUM
			if (isBlinkEnabled() && (led != null))
			{
				led.blink(100);
			}

			// transform the MQTT payload in a String
			String msg = new String(payload);
			
			// log message only if enabled
			msgLog(msg);

			// NO test mode... send the msg to IoT
			if (!isTestMode())
			{
				// switch eliminated using polimorphism and set of derived classes
				iotClient.send(parser.parse(msg));
			}
		}

	}
	
	private void dump(String msg_type, String msg)
	{
		// this object take care of the appropriate action for each type of msg
		new MessageDumper().dump(msg_type, msg);
	}
	
	
	@Override
	public void onMessageConfirmed(int arg0, String arg1)
	{

	}

	@Override
	public void onMessagePublished(int arg0, String arg1)
	{

	}

	private void subscribe(String TOPIC)
	{
		try
		{
			if ((_dataService != null) && (_dataService.isConnected()))
			{
				// subscribe to MQTT topic on local broker
				_dataService.subscribe(TOPIC, 1);

				debug("subscription done to topic " + TOPIC);
			}
		} catch (Exception e)
		{
			error("failed to subscribe to topic: " + e);
		}
	}

	private void unSubscribe(String TOPIC)
	{
		try
		{
			if ((_dataService != null) && (_dataService.isConnected()))
			{
				_dataService.unsubscribe(TOPIC);

				debug("unsubscribe done on " + TOPIC);
			}

		} catch (Exception e)
		{
			error("failed to unsubscribe to topic: " + e);
		}
	}

	/**
	 * 
	 * Used to dump bundle configs
	 * 
	 * @param properties
	 */
	private void dumpProperties()
	{
		if (this.properties != null && !this.properties.isEmpty())
		{
			Iterator<Entry<String, Object>> it = this.properties.entrySet().iterator();

			info("Properties:.................");
			while (it.hasNext())
			{
				Entry<String, Object> entry = it.next();

				if (entry.getKey().equals("ta.pwd"))
				{
					String pwd = (String) entry.getValue();

					try
					{
						// must decrypt
						info("Property - " + "ta.pwd" + " = " + decrypt(pwd) + " of type "
								+ entry.getValue().getClass().toString());
					} catch (final Exception e)
					{
						e.printStackTrace();
					}
				} else
				{
					info("Property - " + entry.getKey() + " = " + entry.getValue() + " of type "
							+ entry.getValue().getClass().toString());
				}

			}
		}
	}

	/*
	 * 
	 * returns one of the memorized properties (in this)
	 * 
	 */
	private Object getProperty(String label)
	{
		return this.properties.get(label);
	}

	private boolean hasPropertyChanged(Map<String, Object> newSet, String label)
	{
		// compare with oldSet
		if (!getProperty(label).equals(newSet.get(label)))
			return true; // changed
		else
			return false;
	}

	/*
	 * 
	 * introduced to support the definition of TOPIC with wildcard single level (+)
	 * for example device/+/data
	 * 
	 * check if msgTopic is compatible with definedTopic (using +)
	 * this way we can subscribe to a SET of topics
	 */
	private boolean areCompatibleTopics(String msgTopic, String definedTopic)
	{
		String SEPARATOR = "/";
		
		boolean vRit = true;

		// split strings
		String[] parts1 = msgTopic.split(SEPARATOR);

		String[] parts2 = definedTopic.split(SEPARATOR);

		// now compare except for +

		for (int i = 0; i < parts1.length; i++)
		{
			// compare parts1[i] with parts2[i]
			if (!parts2[i].equals("+")) // otherwise (+) OK and skip
			{
				// compare
				if (!parts2[i].equals(parts1[i]))
				{
					vRit = false;
					break;
				}
			}
		}

		return vRit;
	}

	private boolean isTestMode()
	{
		return (boolean) getProperty("mode.test");
	}

	private boolean isBlinkEnabled()
	{
		return (boolean) getProperty("blink.enable");
	}

	/*
	 * 
	 * to decrypt the pwd, using CryptoService
	 * 
	 */
	private String decrypt(String in)
	{
		String out = null;

		try
		{
			out = String.valueOf(this._cryptoService.decryptAes(in.toCharArray()));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return out;
	}
	
	/*
	 * 
	 * log incoming messages. If test mode, parse and dump contents
	 * 
	 */
	private void msgLog(String msg)
	{
		if ((boolean)getProperty("msglog.enable") == true)
		{
			s_logger.info(msg);
			
			if (isTestMode())
			{
				dump((String)getProperty("msg.type"), msg);
			}
		}
	}
	
	/*
	 * utility methods for logging
	 * 
	 */
	private static void info(String msg)
	{
		s_logger.info(msg);
	}

	private static void debug(String msg)
	{
		s_logger.debug(msg);
	}

	private static void error(String msg)
	{
		s_logger.error(msg);
	}
}
