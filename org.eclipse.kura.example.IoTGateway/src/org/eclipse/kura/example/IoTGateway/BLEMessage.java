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

import com.google.gson.annotations.JsonAdapter;

/*
 * 
 * This class shows how to implement a Custom Deserializer to 
 * deserialize a KuraPayload
 * 
 * it works if you create the deserializer and plug-in using the annotation
 * 
 * 
 */
public class BLEMessage extends Message
{
	@JsonAdapter(BLEMetricsDeserializer.class)
	private BLEMetrics metrics; 
	
	public BLEMetrics getMetrics()
	{
		return metrics;
	}

	public void setMetrics(BLEMetrics metrics)
	{
		this.metrics = metrics;
	}
}
