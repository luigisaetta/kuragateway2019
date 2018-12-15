package org.eclipse.kura.example.IoTGateway;

public class MessageParserFactory
{
	public static String identifyMsgType(String msg)
	{
		// identify msgType from the first message
		String msgType = null;
		
		if (msg.contains("MAF"))
			msgType = "OBD2";
		else
		{
			if (msg.contains("rssi"))
				msgType = "BLE";
			else
				msgType = "TEMP";
		}
		return msgType;
	}
	
	public static MessageParser createParser(String msgType)
	{
		MessageParser processor = null;
		
		
		switch (msgType)
		{
			case "TEMP":
				processor = new TempMessageParser();
				break;
			
			case "OBD2":
				processor = new OBD2MessageParser();
				break;
			
			case "BLE":
				processor = new BLEMessageParser();
				break;
				
			case "EDISON":
				processor = new EdisonMessageParser();
				break;
			
			case "SMART_LIGHT":
				processor = new SmartLightMessageParser();
				break;
			case "AIRCARE":
				processor = new AircareMessageParser();
				break;
		}
		return processor;
	}
}
