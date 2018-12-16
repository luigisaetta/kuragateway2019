package org.eclipse.kura.example.IoTGateway;

import com.google.gson.Gson;

public class AircareMessageParser extends MessageParser
{
	int MIN_LENGTH = 60;

	Gson gson = new Gson();
	
	public AircareMessage parse(String mess)
	{
		AircareMessage outMess = null;

		if (isPayloadOK(mess))
		{
			try
			{
				mess = encodeUTF8(mess);
				
				outMess = gson.fromJson(mess, AircareMessage.class);
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
