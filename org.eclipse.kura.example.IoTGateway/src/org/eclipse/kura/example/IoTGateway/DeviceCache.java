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

import java.util.Hashtable;
import oracle.iot.client.device.VirtualDevice;

/*
* 
* This class wraps an Hashtable that contains
* virtuaDevices handles
* 
* to enable future better implementation
* for example as a distributed cache (Coherence, Hazelcast)
* 
*/
public class DeviceCache
{
	// the hashtable containing the list of devices already registered
	private Hashtable<String, VirtualDevice> hDevices = new Hashtable<String, VirtualDevice>();

	public DeviceCache()
	{

	}

	public synchronized void put(String key, VirtualDevice vDev)
	{
		hDevices.put(key, vDev);
	}

	public synchronized VirtualDevice get(String key)
	{
		return hDevices.get(key);
	}
}

