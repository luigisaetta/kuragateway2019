package org.eclipse.kura.example.IoTGateway;

/*
 * 
 * Dicembre 2019
 * 
 */
public class IoTClientFactory
{
	public static OracleIoTBaseClient createIoTClient(String msgType,String tasPath, String tasPwd )
	{
		OracleIoTBaseClient iotClient = null;
		
		
		switch (msgType)
		{
			case "TEMP":
				iotClient = new OracleIoTTempClient(tasPath, tasPwd);
				break;
			
			case "OBD2":
				iotClient = new OracleIoTOBD2Client(tasPath, tasPwd);
				break;
			
			case "BLE":
				iotClient = new OracleIoTBLEClient(tasPath, tasPwd);
				break;
				
			case "EDISON":
				iotClient = new OracleIoTEdisonClient(tasPath, tasPwd);
				break;
			
			case "SMART_LIGHT":
				iotClient = new OracleIoTSmartLightClient(tasPath, tasPwd);
				break;
			case "AIRCARE":
				iotClient = new OracleIoTAircareClient(tasPath, tasPwd);
				break;
		}
		return iotClient;
	}
}
