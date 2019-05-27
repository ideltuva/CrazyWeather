package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.Period;
import domain.Weather;
import domain.Wrapper;
import org.springframework.stereotype.Component;

import javax.naming.event.ObjectChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JSONParser {

    static String URL = "https://github.sebank.se/raw/seb-common/Mob-programming/master/weather.json";

    private static Gson gson = new Gson();

    private static String getResponse(String serviceUrl) throws IOException {


        URL url = new URL(serviceUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        StringBuilder strBuilder = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            strBuilder.append(line);
        }

        conn.disconnect();

        return strBuilder.toString();

    }

    public static Wrapper getWrapper() throws IOException {
        final GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Wrapper.class, new WrapperDeserializer());
        gsonBuilder.registerTypeAdapter(Period.class, new PeriodDeserializer());
        gsonBuilder.registerTypeAdapter(Weather.class, new WeatherDeserializer());

        final Gson gson = gsonBuilder.create();

        // Parse JSON to Java
        final Wrapper wrapper = gson.fromJson(getResponse(URL), Wrapper.class);

        return wrapper;
    }

















}