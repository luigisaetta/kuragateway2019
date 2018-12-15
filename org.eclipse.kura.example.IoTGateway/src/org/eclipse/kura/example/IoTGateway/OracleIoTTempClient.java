package org.eclipse.kura.example.IoTGateway;

import oracle.iot.client.device.VirtualDevice;

public class OracleIoTTempClient extends OracleIoTBaseClient
{
	public OracleIoTTempClient(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}

	public void send(Message gMsg)
	{
		VirtualDevice virtualDevice = null;

		TempMessage msg = (TempMessage) gMsg;

		// added check to avoid NPE
		if ((msg == null) || (msg.getDeviceId() == null))
			return;

		info("sending to Oracle Iot TEMP = " + msg.getTemp());

		try
		{
			//
			// data set to zero are not available now
			//
			debug("Trying to sending to Iot...");

			// Lazy Registration of Device
			virtualDevice = getVirtualDevice(msg.getDeviceId(), TemperatureModel.TEMP_URN_MSG);

			if (virtualDevice != null)
			{
				// name of attributes in Oracle DeviceModel are encapsulated in TemperatureModel
				virtualDevice.update().set(TemperatureModel.LATITUDE, msg.getLat())
						.set(TemperatureModel.LONGITUDE, msg.getLng())
						.set(TemperatureModel.TEMP, msg.getTemp())
						.set(TemperatureModel.HUM, msg.getHum())
						.set(TemperatureModel.LIGHT, msg.getLight())
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
