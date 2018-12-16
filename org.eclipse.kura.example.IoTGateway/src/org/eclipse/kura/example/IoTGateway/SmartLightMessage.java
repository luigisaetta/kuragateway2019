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

import com.google.gson.annotations.SerializedName;

public class SmartLightMessage extends Message
{
	@SerializedName("DEV_ID")
	private String deviceId;
	
	@SerializedName("TSTAMP")
	private long tStamp;
	
	@SerializedName("LIGHT_STATUS")
	private String lightStatus;
	
	public String getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}

	public long gettStamp()
	{
		return tStamp;
	}

	public void settStamp(long tStamp)
	{
		this.tStamp = tStamp;
	}

	public String getLightStatus()
	{
		return lightStatus;
	}

	public void setLightStatus(String lightStatus)
	{
		this.lightStatus = lightStatus;
	}
}
