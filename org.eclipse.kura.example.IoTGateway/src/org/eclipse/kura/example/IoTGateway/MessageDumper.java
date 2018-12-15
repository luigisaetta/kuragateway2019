package org.eclipse.kura.example.IoTGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 
 * This class print in the log the main information of the Msg
 * 
 * it has been added in order to declutter the IoTGateway code
 * 
 * 
 * 
 */
public class MessageDumper
{
	private static final Logger s_logger = LoggerFactory.getLogger(MessageDumper.class);
	
	public void dump(String messageType, String msg)
	{
		MessageParser parser = MessageParserFactory.createParser(messageType);
		
		switch (messageType)
		{
		case "BLE":
			BLEMessage bMsg = (BLEMessage) parser.parse(msg);

			if ((bMsg != null) && (bMsg.getMetrics() != null))
			{
				info("UUid: " + bMsg.getMetrics().getUuid());
				info("Major: " + bMsg.getMetrics().getMajor());
				info("Minor: " + bMsg.getMetrics().getMinor());
			}
			break;
		
		case "EDISON":
			EdisonMessage eMsg = (EdisonMessage) parser.parse(msg);

			if (eMsg != null)
			{
				info("Temp: " + eMsg.getData().getTemp());
				info("Air Quality: " + eMsg.getData().getAirQ());
				info("Light: " + eMsg.getData().getLight());
				info("Gas: " + eMsg.getData().getGas());
			}
			break;

		case "SMART_LIGHT":
			SmartLightMessage sMsg = (SmartLightMessage) parser.parse(msg);

			if (sMsg != null)
			{
				info("Light Status: " + sMsg.getLightStatus());
			}
			break;
		
		case "TEMP":
			TempMessage tMsg = (TempMessage) parser.parse(msg);

			if (tMsg != null)
			{
				info("Temp: " + tMsg.getTemp());
				info("Hum: " + tMsg.getHum());
				info("Light:" + tMsg.getLight());
			}
			break;
			
		case "AIRCARE":
			AircareMessage aMsg = (AircareMessage) parser.parse(msg);

			if (aMsg != null)
			{
				info("Temp: " + aMsg.getTemp());
				info("Hum: " + aMsg.getHum());
				info("Light:" + aMsg.getLight());
			}
			break;
		
		case "OBD2":
			OBD2Message oMsg = (OBD2Message) parser.parse(msg);

			if (oMsg != null)
			{
				info("CARID: " + oMsg.getCarId());
				info("Coolant Temp: " + oMsg.getCoolantTemp());
			}
			break;
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
}
