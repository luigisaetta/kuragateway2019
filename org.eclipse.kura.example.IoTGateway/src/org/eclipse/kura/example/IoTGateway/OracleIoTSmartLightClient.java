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

import oracle.iot.client.device.VirtualDevice;

public class OracleIoTSmartLightClient extends OracleIoTBaseClient
{
	public OracleIoTSmartLightClient(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}

	public void send(Message gMsg)
	{
		VirtualDevice virtualDevice = null;
		
		SmartLightMessage msg = (SmartLightMessage) gMsg;
		
		// added check to avoid NPE
		if ((msg == null) || (msg.getDeviceId() == null))
			return;

		try
		{
			//
			// data set to zero are not available now
			//
			debug("Trying to sending to Iot...");

			// Lazy Registration of Device
			virtualDevice = getVirtualDevice(msg.getDeviceId(), SmartLightModel.LIGHT_URN_MSG);

			if (virtualDevice != null)
			{
				// name of attributes in Oracle DeviceModel are encapsulated in TemperatureModel
				virtualDevice.update()
				.set(SmartLightModel.LIGHT_STATUS, msg.getLightStatus())
				.finish();

				info("Msg sent to Iot...");
			}

		} catch (Exception e)
		{
			// continue
			e.printStackTrace();
		}
	}
}
