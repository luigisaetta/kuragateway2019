package org.eclipse.kura.example.IoTGateway;

import java.util.ArrayList;

/*
 * 
 * Dicembre 2019
 * 
 */
public class IoTClientFactory
{
	public static ArrayList<OracleIoTBaseClient> createIoTClient(String tasPath, String tasPwd )
	{
		ArrayList<OracleIoTBaseClient> iotClients = new ArrayList<OracleIoTBaseClient>();
		
		iotClients.add(new OracleIoTAircareClient(tasPath, tasPwd));
		iotClients.add(new OracleIoTEdisonClient(tasPath, tasPwd));
		iotClients.add(new OracleIoTOBD2Client(tasPath, tasPwd));
		iotClients.add(new OracleIoTTempClient(tasPath, tasPwd));
		
		/*switch (msgType)
		{	
			case "BLE":
				iotClient = new OracleIoTBLEClient(tasPath, tasPwd);
				break;
				
			
			case "SMART_LIGHT":
				iotClient = new OracleIoTSmartLightClient(tasPath, tasPwd);
				break;
		}*/
		
		return iotClients;
	}
}
