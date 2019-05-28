package service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.Period;
import domain.Weather;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PeriodDeserializer implements JsonDeserializer<Period> {


    private final ThreadLocal<Map<String, Period>> cache = new ThreadLocal<Map<String, Period>>() {
        @Override
        protected Map<String, Period> initialValue() {
            return new HashMap<>();
        }
    };


    @Override
    public Period deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String validTime = jsonObject.get("validTime").getAsString();
        Weather[] weathers = context.deserialize(jsonObject.get("parameters"), Weather[].class);

        final Period period = createPeriodMap(validTime);
        period.setWeather(weathers);

        return period;
    }

    private Period createPeriodMap(String validTime) {

        Period period = cache.get().get(validTime);
        if (period == null) {
            period = new Period();
            period.setValidTime(validTime);
            cache.get().put(validTime, period);
        }
        return period;
    }


}
