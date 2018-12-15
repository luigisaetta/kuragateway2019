package org.eclipse.kura.example.IoTGateway;

import oracle.iot.client.device.VirtualDevice;

public class OracleIoTAircareClient extends OracleIoTBaseClient
{
	public OracleIoTAircareClient(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}

	public void send(Message gMsg)
	{
		VirtualDevice virtualDevice = null;
		
		AircareMessage msg = (AircareMessage) gMsg;
		
		// added check to avoid NPE
		if ((msg == null))
			return;

		try
		{
			debug("Trying to sending to Iot...");

			// Lazy Registration of Device

			// TODO.. here I have forced deviceId == aircare (should better implement)
			virtualDevice = getVirtualDevice("aircare", AircareModel.AIRCARE_URN_MSG);

			if (virtualDevice != null)
			{
				// name of attributes in Oracle DeviceModel are encapsulated in AircareModel
				virtualDevice.update()
				        .set(AircareModel.TEMP, msg.getTemp())
				        .set(AircareModel.HUMIDITY, msg.getHum())
						.set(AircareModel.LIGHT, msg.getLight())
						.set(AircareModel.PM10, msg.getPm10())
						.set(AircareModel.PM25, msg.getPm25())
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
