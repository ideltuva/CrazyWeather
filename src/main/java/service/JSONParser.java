package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Period;
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


    public static Period[] getWeatherData() throws IOException {
        Gson gson = new Gson();

        String jsonString = getResponse(URL);
        Wrapper wrapper = gson.fromJson(jsonString, Wrapper.class);

        return wrapper.getPeriods();
    }

    public static List<Period> getPeriod() throws IOException {
        final List<Period> rows = Arrays.asList(getWeatherData());

        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        List<Period> result = rows.stream()
                .filter(r -> "2019-05-16T06:00:00Z".equals(r.validTime))
                .collect(Collectors.toList());

        return result;
    }















}