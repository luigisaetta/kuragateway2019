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

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EdisonDeserializer implements JsonDeserializer<EdisonData> 
{

	@Override
	public EdisonData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException 
    {
        JsonObject jsonObject = json.getAsJsonObject();

        return new EdisonData(
            jsonObject.get("Temp").getAsString(),
            jsonObject.get("Hum").getAsString(),
            jsonObject.get("Light").getAsString(),
            jsonObject.get("AirQ").getAsString(),
            jsonObject.get("Gas").getAsString()
        );
    }

}
