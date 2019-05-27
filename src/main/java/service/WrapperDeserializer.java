package service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.Period;
import domain.Wrapper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class WrapperDeserializer implements JsonDeserializer<Wrapper> {

    public final ThreadLocal<Map<String, Period>> cache = new ThreadLocal<Map<String, Period>>() {
        @Override
        protected Map<String, Period> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Wrapper deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {

        final JsonObject jsonObject = json.getAsJsonObject();
        Period[] periods = context.deserialize(jsonObject.get("timeSeries"), Period[].class);

        final Wrapper wrapper = new Wrapper();

        wrapper.setPeriods(periods);

        return wrapper;
    }






}
