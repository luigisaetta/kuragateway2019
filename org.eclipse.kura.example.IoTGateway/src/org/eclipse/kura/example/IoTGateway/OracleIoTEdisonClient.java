package org.eclipse.kura.example.IoTGateway;

import oracle.iot.client.device.VirtualDevice;

public class OracleIoTEdisonClient extends OracleIoTBaseClient
{
	public OracleIoTEdisonClient(String tasPath, String tasPwd)
	{
		super(tasPath, tasPwd);
	}

	public void send(Message gMsg)
	{
		VirtualDevice virtualDevice = null;

		EdisonMessage msg = (EdisonMessage) gMsg;
		
		// added check to avoid NPE
		if ((msg == null) || (msg.getSource() == null))
			return;

		try
		{
			//
			// data set to zero are not available now
			//
			debug("Trying to sending to Iot...");

			// Lazy Registration of Device
			virtualDevice = getVirtualDevice(msg.getSource(), EdisonModel.EDI_URN_MSG);

			if (virtualDevice != null)
			{
				// name of attributes in Oracle DeviceModel are encapsulated in TemperatureModel
				virtualDevice.update()
				       .set(EdisonModel.TEMP, msg.getData().getTemp())
						.set(EdisonModel.HUMIDITY, msg.getData().getHum())
						.set(EdisonModel.LIGHT, msg.getData().getLight())
						.set(EdisonModel.AIRQ, msg.getData().getAirQ())
						.set(EdisonModel.GAS, msg.getData().getGas())
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
