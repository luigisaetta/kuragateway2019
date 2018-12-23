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

/*
 * 
 * Dicembre 2019
 * 
 */
public class IoTClientFactory
{
	public static ArrayList<OracleIoTBaseClient> createIoTClients(String tasPath, String tasPwd)
	{
		ArrayList<OracleIoTBaseClient> iotClients = new ArrayList<OracleIoTBaseClient>();
		
		iotClients.add(new OracleIoTAircareClient(tasPath, tasPwd));
		iotClients.add(new OracleIoTEdisonClient(tasPath, tasPwd));
		iotClients.add(new OracleIoTOBD2Client(tasPath, tasPwd));
		iotClients.add(new OracleIoTTempClient(tasPath, tasPwd));
		iotClients.add(new OracleIoTBLEClient(tasPath, tasPwd));
	    iotClients.add(new OracleIoTSmartLightClient(tasPath, tasPwd));
		
		return iotClients;
	}
}
