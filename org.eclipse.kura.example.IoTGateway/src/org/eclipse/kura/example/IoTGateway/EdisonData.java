package org.eclipse.kura.example.IoTGateway;

public class EdisonData
{
	private String Temp;
	private String Hum;
	private String Light;
	private String AirQ;
	private String Gas;
	
	public EdisonData(String theTemp, String theHum, String theLight, String theAirQ, String theGas)
	{
		this.Temp = theTemp;
		this.Hum = theHum;
		this.Light = theLight;
		this.AirQ = theAirQ;
		this.Gas = theGas;
	}
	
	public double getTemp()
	{
		return Double.parseDouble(Temp);
	}
	public void setTemp(String temp)
	{
		Temp = temp;
	}
	public Double getHum()
	{
		return Double.parseDouble(Hum);
	}
	public void setHum(String hum)
	{
		Hum = hum;
	}
	public Double getLight()
	{
		return Double.parseDouble(Light);
	}
	public void setLight(String light)
	{
		Light = light;
	}
	public Double getAirQ()
	{
		return Double.parseDouble(AirQ);
	}
	public void setAirQ(String airQ)
	{
		AirQ = airQ;
	}
	public Double getGas()
	{
		return Double.parseDouble(Gas);
	}
	public void setGas(String gas)
	{
		Gas = gas;
	}
	
	
}
