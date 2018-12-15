package org.eclipse.kura.example.IoTGateway;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class EdisonMessageParser extends MessageParser
{
	int MIN_LENGTH = 70;

	Gson gson = new Gson();
	
	public EdisonMessage parse(String mess)
	{
		EdisonMessage outMess = null;

		mess = encodeUTF8(mess);

		if (isPayloadOK(mess))
		{
			info("Parsing...");
			
			try
			{
				outMess = gson.fromJson(mess, EdisonMessage.class);
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
