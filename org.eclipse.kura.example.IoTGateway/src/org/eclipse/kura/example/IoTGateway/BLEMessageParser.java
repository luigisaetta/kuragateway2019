package org.eclipse.kura.example.IoTGateway;

import com.google.gson.Gson;

public class BLEMessageParser extends MessageParser
{
	// used for the isPayloadOK
	int MIN_LENGTH = 80;

	Gson gson = new Gson();
	
	public BLEMessage parse(String mess)
	{
		BLEMessage outMess = null;

		mess = encodeUTF8(mess);

		if (isPayloadOK(mess))
		{
			info("Parsing...");
			
			try
			{
				outMess = gson.fromJson(mess, BLEMessage.class);
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
