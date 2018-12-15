package org.eclipse.kura.example.IoTGateway;

import com.google.gson.annotations.SerializedName;

public class TempMessage extends Message
{
	@SerializedName("DEV_ID")
	String deviceId;
	
	@SerializedName("LAT")
	double lat;
	@SerializedName("LON")
	double lng;
	@SerializedName("TEMP")
	double temp;
	@SerializedName("HUM")
	double hum;
	@SerializedName("LIGHT")
	int light;
	
	public double getLat()
	{
		return lat;
	}
	public double getHum()
	{
		return hum;
	}
	public void setHum(double hum)
	{
		this.hum = hum;
	}
	public int getLight()
	{
		return light;
	}
	public void setLight(int light)
	{
		this.light = light;
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
	public double getTemp()
	{
		return temp;
	}
	public void setTemp(double temp)
	{
		this.temp = temp;
	}
	public String getDeviceId()
	{
		return deviceId;
	}
	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}
}
