package org.eclipse.kura.example.IoTGateway;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SmartLightMessageParser extends MessageParser
{
	int MIN_LENGTH = 10;

	Gson gson = new Gson();

	public SmartLightMessage parse(String mess)
	{
		SmartLightMessage outMess = null;

		mess = encodeUTF8(mess);

		if (isPayloadOK(mess))
		{
			info("Parsing...");
			
			try
			{
				outMess = gson.fromJson(mess, SmartLightMessage.class);
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
