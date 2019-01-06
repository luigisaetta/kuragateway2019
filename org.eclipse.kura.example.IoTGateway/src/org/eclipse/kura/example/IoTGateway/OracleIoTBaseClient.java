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

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.iot.client.*;
import oracle.iot.client.device.*;

public class OracleIoTBaseClient
{
	private static final Logger s_logger = LoggerFactory.getLogger(OracleIoTBaseClient.class);
	
	/* integration with Oracle IoT
	* the Device Model used is
	* encapsulated in XXXXModel
	*/

	// path and name of trusted assett store (from Device registration)
	private static String TA_STORE_PATH = null;
	private static String TA_STORE_PWD = null;

	private GatewayDevice gw = null;
	
	// Hashtable to store Device Models
	private Hashtable<String, DeviceModel> tableDevModel = new Hashtable<String, DeviceModel>();
		
	// Cache containing the list of devices already registered with this gateway
	DeviceCache hDevices = new DeviceCache();
	
	public OracleIoTBaseClient(String tasPath, String tasPwd)
	{
		TA_STORE_PATH = tasPath;
		TA_STORE_PWD = tasPwd;
	}

	/*
	 * 
	 * Activation of Oracle Gateway (the Kura one)
	 * 
	 */
	public void activateOracleGateway()
	{
		try
		{
			debug("Creating the Gateway !");
			
			gw = new GatewayDevice(TA_STORE_PATH, TA_STORE_PWD);

			if (!gw.isActivated())
			{
				info("Activating the Gateway !");
				
				gw.activate();
			} else
			{
				info("Gateway already activated !");
			}

			debug("OK after Gateway activation ...");

			// download list of supported device models
			// use an hashmap, with the urn as a key
			downloadDeviceModels();
			
		} catch (Exception e)
		{
			error("in activating gateway...");
			
			error(e.getMessage());
			error(e.toString());
			
			e.printStackTrace();

			return;
		}
	}
	
	protected void downloadDeviceModels()
	{
		DeviceModel deviceModel = null;
		
		// OBD2, TEMP, LIGHT, EDISON, AIRCARE
		try
		{
			List<String> modelList = new ArrayList<String>();
			
			// define list of supported DeviceModel
			modelList.add(AircareModel.AIRCARE_URN_MSG);
			modelList.add(EdisonModel.EDI_URN_MSG);
			modelList.add(OBD2Model.OBD2_URN_MSG);
			modelList.add(SmartLightModel.LIGHT_URN_MSG);
			modelList.add(TemperatureModel.TEMP_URN_MSG);
			
			// load in tableDevModel
			for(String modelType : modelList) 
			{
				deviceModel = gw.getDeviceModel(modelType);
				
				if (deviceModel != null)
				{
					if (tableDevModel.get(modelType) == null)
						tableDevModel.put(modelType, deviceModel);
					
					debug(deviceModel.getURN());
				}
			}
		} catch (Exception e)
		{
			error("in downloadDeviceModels...");
			
			error(e.getMessage());
			error(e.toString());
			return;
		}
	}

	public void send(Message gMsg)
	{
		// this shouldn't never be really called...
		info("Calling the method send of the base client !!!");
	}
	
	/*
	 * 
	 * get Virtual Device from hashmap... if needed register
	 * 
	 * 
	 */
	protected VirtualDevice getVirtualDevice(String msgDeviceId, String deviceModelUrn)
	{
		String deviceId = null;
		VirtualDevice virtualDevice = null;
		
		// Lazy Registration of Device
		if (hDevices.get(msgDeviceId) == null)
		{
			// NOT FOUND in HashTable, register it

			// add any vendor-specific meta-data to the metaData Map
			Map<String, String> metaData = initMetadataMap(msgDeviceId);

			try
			{
				// getDeviceId (carID) as DEVICE_ACTIVATION_ID
				deviceId = gw.registerDevice(msgDeviceId, metaData, deviceModelUrn);

				info("OK after device registration: " + deviceId);
				
				// Modified, now, search in hashtable
				DeviceModel deviceModel = tableDevModel.get(deviceModelUrn);
				
				
				virtualDevice = gw.createVirtualDevice(deviceId, deviceModel);

				// save in Hashtable
				debug("save in hashtable");
				
				hDevices.put(msgDeviceId, virtualDevice);
			} catch (Exception e)
			{
				e.printStackTrace();

				return null;
			}
		}
		else
		{
			virtualDevice = hDevices.get(msgDeviceId);
		}
		
		return virtualDevice;
	}

	/**
	 * init metadata Map
	 */
	protected Map<String, String> initMetadataMap(String deviceActivationId)
	{
		// create meta-data with the indirectly-connected device's
		// manufacturer, model, and serial number
		Map<String, String> metaData = new HashMap<String, String>();

		metaData.put(oracle.iot.client.device.GatewayDevice.MANUFACTURER, OBD2Model.MANUFACTURER);
		metaData.put(oracle.iot.client.device.GatewayDevice.MODEL_NUMBER, OBD2Model.DEVICE_MODEL_NUMBER);
		metaData.put(oracle.iot.client.device.GatewayDevice.SERIAL_NUMBER, deviceActivationId);

		return metaData;
	}

	/*
	 * utility methods for logging
	 * 
	 */
	protected void info(String msg)
	{
		s_logger.info(msg);
	}

	protected void debug(String msg)
	{
		s_logger.debug(msg);
	}
	protected static void error(String msg)
	{
		s_logger.error(msg);
	}
}
