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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class TempMessageParser extends MessageParser
{
	int MIN_LENGTH = 6;

	Gson gson = new Gson();

	public TempMessage parse(String mess)
	{
		TempMessage outMess = null;

		mess = encodeUTF8(mess);

		if (isPayloadOK(mess))
		{
			info("Parsing...");
			
			try
			{
				outMess = gson.fromJson(mess, TempMessage.class);
			} catch (JsonSyntaxException e)
			{
				error("Malformed request!");
				e.printStackTrace();
			}

		} else
		{
			error("Malformed request!");
		}
		return outMess;
	}
}
