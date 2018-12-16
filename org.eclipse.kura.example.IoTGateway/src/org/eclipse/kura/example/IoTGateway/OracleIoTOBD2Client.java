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

public class OracleIoTOBD2Client extends OracleIoTBaseClient
{
	public OracleIoTOBD2Client(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}

	public void send(Message gMsg)
	{
		VirtualDevice virtualDevice = null;

		OBD2Message msg = (OBD2Message) gMsg;
		
		// added check to avoid NPE
		if ((msg == null) || (msg.getDeviceId() == null))
			return;

		try
		{
			debug("Trying to sending to Iot...");

			// Lazy Registration of Device
			virtualDevice = getVirtualDevice(msg.getDeviceId(), OBD2Model.OBD2_URN_MSG);

			if (virtualDevice != null)
			{
				// name of attributes in Oracle DeviceModel are encapsulated in OBD2Model
				virtualDevice.update()
				        .set(OBD2Model.LATITUDE, msg.getLat())
				        .set(OBD2Model.LONGITUDE, msg.getLng())
						.set(OBD2Model.ALTITUDE, msg.getAlt())
						.set(OBD2Model.SPEED, msg.getSpeed())
						.set(OBD2Model.RPM, msg.getRpmAsLong())
						.set(OBD2Model.COOLANT_TEMPERATURE, msg.getCoolantTemp())
						.set(OBD2Model.MAF, msg.getMaf())
						.set(OBD2Model.RUNTIME_SINCE_ENGINE_START, msg.getRuntime())
						.set(OBD2Model.DTCS, 0)
						.finish();

				info("OK: Msg sent to Iot...");
			}

		} catch (Exception e)
		{
			// continue
			e.printStackTrace();
		}
	}
}
