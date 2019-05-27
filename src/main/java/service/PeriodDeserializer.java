package service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.Period;
import domain.Weather;

import java.lang.reflect.Type;

public class PeriodDeserializer implements JsonDeserializer {

    @Override
    public Period deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String validTime = jsonObject.get("validTime").getAsString();
        Weather[] weathers = context.deserialize(jsonObject.get("parameters"), Weather[].class);

        final Period period = new Period();

        period.setValidTime(validTime);
        period.setWeather(weathers);

        return period;
    }

}
