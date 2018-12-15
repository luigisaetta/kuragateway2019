package org.eclipse.kura.example.IoTGateway;

public interface OBD2Model
{
	public static final String OBD2_URN_MSG = "urn:com:oracle:iot:device:obd2";
	// name of attributes in Oracle DeviceModel
	public static final String DTCS = "ora_obd2_number_of_dtcs";
	public static final String RUNTIME_SINCE_ENGINE_START = "ora_obd2_runtime_since_engine_start";
	public static final String MAF = "ora_obd2_mass_air_flow";
	public static final String COOLANT_TEMPERATURE = "ora_obd2_engine_coolant_temperature";
	public static final String RPM = "ora_obd2_engine_rpm";
	public static final String SPEED = "ora_obd2_vehicle_speed";
	public static final String ALTITUDE = "ora_altitude";
	public static final String LONGITUDE = "ora_longitude";
	public static final String LATITUDE = "ora_latitude";
	
	public static final String MANUFACTURER = "Saetta Corporation";
	public static final String DEVICE_MODEL_NUMBER = "MN101";
}
