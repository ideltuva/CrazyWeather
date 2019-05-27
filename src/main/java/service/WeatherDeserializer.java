package service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.Weather;

import java.lang.reflect.Type;

public class WeatherDeserializer implements JsonDeserializer {

    @Override
    public Weather deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final JsonObject jsonObject = json.getAsJsonObject();
        final Weather weather = new Weather();

        weather.setLevel(jsonObject.get("level").getAsString());
        weather.setLevelType(jsonObject.get("levelType").getAsString());
        weather.setName(jsonObject.get("name").getAsString());
        weather.setUnit(jsonObject.get("unit").getAsString());
        double[] values = context.deserialize(jsonObject.get("values"), double[].class);
        weather.setValues(values);

        return weather;
    }






}
