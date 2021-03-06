/*
 * Luigi Saetta
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Dicembre 2019
 * 
 */
package org.eclipse.kura.example.IoTGateway;

import java.util.ArrayList;

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
	
	
	public void dump(String msg)
	{
		ArrayList<MessageParser> parserArr = MessageParserFactory.createParsers();
		
		// recognize msgType
		int msgIndex = MessageParserFactory.recognizeMsg(msg);
		
		switch (msgIndex)
		{
		 case MessageParserFactory.TYPE_BLE:
			BLEMessage bMsg = (BLEMessage) parserArr.get(msgIndex).parse(msg);

			if ((bMsg != null) && (bMsg.getMetrics() != null))
			{
				info("UUid: " + bMsg.getMetrics().getUuid());
				info("Major: " + bMsg.getMetrics().getMajor());
				info("Minor: " + bMsg.getMetrics().getMinor());
			}
			break;
		
		case MessageParserFactory.TYPE_EDISON:
			EdisonMessage eMsg = (EdisonMessage) parserArr.get(msgIndex).parse(msg);

			if (eMsg != null)
			{
				info("Temp: " + eMsg.getData().getTemp());
				info("Air Quality: " + eMsg.getData().getAirQ());
				info("Light: " + eMsg.getData().getLight());
				info("Gas: " + eMsg.getData().getGas());
			}
			break;

		case MessageParserFactory.TYPE_LIGHT:
			SmartLightMessage sMsg = (SmartLightMessage) parserArr.get(msgIndex).parse(msg);

			if (sMsg != null)
			{
				info("Light Status: " + sMsg.getLightStatus());
			}
			break;
		
		case MessageParserFactory.TYPE_TEMP:
			TempMessage tMsg = (TempMessage) parserArr.get(msgIndex).parse(msg);

			if (tMsg != null)
			{
				info("Temp: " + tMsg.getTemp());
				info("Hum: " + tMsg.getHum());
				info("Light:" + tMsg.getLight());
			}
			break;
			
		case MessageParserFactory.TYPE_AIRCARE:
			AircareMessage aMsg = (AircareMessage) parserArr.get(msgIndex).parse(msg);

			if (aMsg != null)
			{
				info("Temp: " + aMsg.getTemp());
				info("Hum: " + aMsg.getHum());
				info("Light:" + aMsg.getLight());
			}
			break;
		
		case MessageParserFactory.TYPE_OBD2:
			OBD2Message oMsg = (OBD2Message) parserArr.get(msgIndex).parse(msg);

			if (oMsg != null)
			{
				info("CARID: " + oMsg.getCarId());
				info("Coolant Temp: " + oMsg.getCoolantTemp());
				info("Speed: " + oMsg.getSpeed());
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
