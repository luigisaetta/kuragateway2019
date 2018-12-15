package org.eclipse.kura.example.IoTGateway;

import com.google.gson.annotations.JsonAdapter;

/*
 * 
 * Introduced to handle message coming from my Edison
 * 
 * {"Source":"edison2","Progr":71,"Tstamp":1532849694588,"Data":{"Temp":"29.0","Light":"758.0","Hum":"0.0","AirQ":"28","Gas":"6"}}
 * 
 */
public class EdisonMessage extends Message
{
	private String Source;
	private int Progr;
	private long Tstamp;
	
	@JsonAdapter(EdisonDeserializer.class)
	private EdisonData Data;
	
	public String getSource()
	{
		return Source;
	}
	public void setSource(String source)
	{
		Source = source;
	}
	public int getProgr()
	{
		return Progr;
	}
	public void setProgr(int progr)
	{
		Progr = progr;
	}
	public long getTstamp()
	{
		return Tstamp;
	}
	public void setTstamp(long tstamp)
	{
		Tstamp = tstamp;
	}
	public EdisonData getData()
	{
		return Data;
	}
	public void setData(EdisonData data)
	{
		Data = data;
	}
}
