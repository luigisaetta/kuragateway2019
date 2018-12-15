package org.eclipse.kura.example.IoTGateway;

public class OracleIoTBLEClient extends OracleIoTBaseClient
{
	public OracleIoTBLEClient(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}
	
	public void send(Message msg)
	{
		// still in test
		info("Msg sent to Iot...");
	}

}
