package org.eclipse.kura.example.IoTGateway;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BLEMetricsDeserializer implements JsonDeserializer<BLEMetrics> 
{  
    @Override
    public BLEMetrics deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException 
    {
        JsonObject jsonObject = json.getAsJsonObject();

        return new BLEMetrics(
            jsonObject.get("uuid").getAsString(),
            jsonObject.get("major").getAsInt(),
            jsonObject.get("minor").getAsInt()
        );
    }
}
