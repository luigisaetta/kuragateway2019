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
