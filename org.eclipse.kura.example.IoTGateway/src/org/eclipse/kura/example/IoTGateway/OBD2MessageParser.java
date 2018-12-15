package org.eclipse.kura.example.IoTGateway;

import com.google.gson.Gson;

/*
 * 
 * Dicembre 2018
 * 
 */
public class OBD2MessageParser extends MessageParser
{
	int MIN_LENGTH = 70;

	Gson gson = new Gson();

	public OBD2Message parse(String mess)
	{
		OBD2Message outMess = null;

		if (isPayloadOK(mess))
		{
			try
			{
				mess = encodeUTF8(mess);
				
				outMess = gson.fromJson(mess, OBD2Message.class);
			} catch (Exception e)
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
