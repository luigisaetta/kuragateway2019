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

public class MessageParserFactory
{
	private static final int TYPE_AIRCARE = 0;
	private static final int TYPE_EDISON = 1;
	private static final int TYPE_OBD2 = 2;
	private static final int TYPE_TEMP = 3;
	private static final int TYPE_BLE = 4;
	private static final int TYPE_LIGHT = 5;

	public static ArrayList<MessageParser> createParser()
	{
		ArrayList<MessageParser> list = new ArrayList<MessageParser>();

		// MUST be in the same order than the constant on top (TYPE_)
		list.add(new AircareMessageParser());
		list.add(new EdisonMessageParser());
		list.add(new OBD2MessageParser());
		list.add(new TempMessageParser());
		list.add(new BLEMessageParser());
		list.add(new SmartLightMessageParser());

		return list;
	}

	public static int recognizeMsg(String msg)
	{
		int vRit = -1;

		if (msg.toLowerCase().contains("pm10"))
		{
			return TYPE_AIRCARE;
		}
		if (msg.contains("Source"))
		{
			return TYPE_EDISON;
		}
		if (msg.contains("CARID"))
		{
			return TYPE_OBD2;
		}

		if (msg.contains("DEV_ID") && msg.contains("TEMP"))
		{
			return TYPE_TEMP;
		}

		if (msg.toLowerCase().contains("uuid"))
		{
			return TYPE_BLE;
		}

		if (msg.toLowerCase().contains("light_status"))
		{
			return TYPE_LIGHT;
		}

		return vRit;
	}
}
