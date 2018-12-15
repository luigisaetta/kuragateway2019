package org.eclipse.kura.example.IoTGateway;

public interface TemperatureModel
{
	public static final String TEMP_URN_MSG = "urn:com:oracle:iot:device:temperature_sensor";
	
	// name of attributes in Oracle DeviceModel
	public static final String LONGITUDE = "ora_longitude";
	public static final String LATITUDE = "ora_latitude";
	public static final String TEMP = "temp";
	public static final String HUM = "hum";
	public static final String LIGHT = "light";
	
	public static final String MANUFACTURER = "Saetta Corporation";
	public static final String DEVICE_MODEL_NUMBER = "TMN201";
}
