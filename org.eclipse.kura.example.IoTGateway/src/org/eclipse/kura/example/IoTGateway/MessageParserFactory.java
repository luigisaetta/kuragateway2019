package org.eclipse.kura.example.IoTGateway;

import java.util.ArrayList;

public class MessageParserFactory
{
	
	public static ArrayList<MessageParser> createParser()
	{
		ArrayList<MessageParser> list = new ArrayList<MessageParser>();
		
		list.add(new AircareMessageParser());
		list.add(new EdisonMessageParser());
		list.add(new OBD2MessageParser());
		list.add(new TempMessageParser());
		
		return list;
	}
}
