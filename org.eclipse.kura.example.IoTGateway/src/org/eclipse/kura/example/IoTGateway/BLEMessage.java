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
