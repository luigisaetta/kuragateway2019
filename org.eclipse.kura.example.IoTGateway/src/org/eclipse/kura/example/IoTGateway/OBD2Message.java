package org.eclipse.kura.example.IoTGateway;

import com.google.gson.annotations.SerializedName;

public class OBD2Message extends Message
{
	@SerializedName("CARID")
	String carId;
	@SerializedName("LAT")
	double lat;
	@SerializedName("LON")
	double lng;
	@SerializedName("ALT")
	int alt;
	@SerializedName("SPEED")
	int speed;
	@SerializedName("DISTANCE")
	long distance;
	@SerializedName("RPM")
	float rpm;
	@SerializedName("COOLANT_TEMP")
	int coolantTemp;
	@SerializedName("MAF")
	double maf;
	@SerializedName("RUN_TIME")
	int runtime;


	public OBD2Message()
	{

	}

	public String getCarId()
	{
		return carId;
	}

	public void setCarId(String carId)
	{
		this.carId = carId;
	}

	/*
	 * in this case deviceId == carId
	 */
	public String getDeviceId()
	{
		return carId;
	}

	public double getLat()
	{
		return lat;
	}

	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public double getLng()
	{
		return lng;
	}

	public void setLng(double lng)
	{
		this.lng = lng;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public long getDistance()
	{
		return distance;
	}

	public void setDistance(long distance)
	{
		this.distance = distance;
	}

	public float getRpm()
	{
		return rpm;
	}

	public long getRpmAsLong()
	{
		return (long) rpm;
	}

	public void setRpm(float rpm)
	{
		this.rpm = rpm;
	}

	public int getCoolantTemp()
	{
		return coolantTemp;
	}

	public void setCoolantTemp(int coolantTemp)
	{
		this.coolantTemp = coolantTemp;
	}

	public double getMaf()
	{
		return maf;
	}

	public void setMaf(double maf)
	{
		this.maf = maf;
	}

	public int getRuntime()
	{
		return runtime;
	}

	public void setRuntime(int runtime)
	{
		this.runtime = runtime;
	}

	public int getAlt()
	{
		return alt;
	}

	public void setAlt(int alt)
	{
		this.alt = alt;
	}
}

