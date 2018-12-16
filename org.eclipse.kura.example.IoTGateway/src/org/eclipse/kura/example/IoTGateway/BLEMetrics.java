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

public class BLEMetrics
{
	private String uuid;
	private int major;
	private int minor;
	private int rssi;
	private double distance;
	private int txprower;
	
	public BLEMetrics(String theUuid, int theMajor, int theMinor)
	{
		this.uuid = theUuid;
		this.major = theMajor;
		this.minor = theMinor;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	public int getMajor()
	{
		return major;
	}
	public void setMajor(int major)
	{
		this.major = major;
	}
	public int getMinor()
	{
		return minor;
	}
	public void setMinor(int minor)
	{
		this.minor = minor;
	}
	public int getRssi()
	{
		return rssi;
	}
	public void setRssi(int rssi)
	{
		this.rssi = rssi;
	}
	public double getDistance()
	{
		return distance;
	}
	public void setDistance(double distance)
	{
		this.distance = distance;
	}
	public int getTxprower()
	{
		return txprower;
	}
	public void setTxprower(int txprower)
	{
		this.txprower = txprower;
	}
	
}
