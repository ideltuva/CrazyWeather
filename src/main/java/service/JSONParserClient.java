package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.Period;
import domain.Weather;
import domain.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.event.ObjectChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JSONParserClient {

    public String URL = "https://github.sebank.se/raw/seb-common/Mob-programming/master/weather.json";

    public Wrapper wrapper;

    public void setWrapper(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public Wrapper getWrapper() {
        return wrapper;
    }

    public JSONParserClient() {
        Wrapper wrp = getData(URL);
        getPeriodCached(wrp);
        this.wrapper = wrp;
    }

    private final ThreadLocal<Map<String, List<Weather>>> cachePeriod = new ThreadLocal<Map<String, List<Weather>>>() {
        @Override
        protected Map<String, List<Weather>> initialValue() {
            return new HashMap<>();
        }
    };

    private Gson initializeGson() {

        final GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Wrapper.class, new WrapperDeserializer());
        gsonBuilder.registerTypeAdapter(Period.class, new PeriodDeserializer());
        gsonBuilder.registerTypeAdapter(Weather.class, new WeatherDeserializer());
        Gson gson = new Gson();
        gson = gsonBuilder.create();

        return gson;
}



    private String getResponse(String serviceUrl) {

        String responseString = null;

        URL url = null;
        try {
            url = new URL(serviceUrl);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder strBuilder = new StringBuilder();
            String line = null;

            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                strBuilder.append(line);
            }

            conn.disconnect();

            responseString = strBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    private Wrapper getData(String url){

        final Gson gson = initializeGson();

        // Parse JSON to Java
        final Wrapper wrapper = gson.fromJson(getResponse(url), Wrapper.class);

        getPeriodCached(wrapper);
        setWrapper(wrapper);

        return wrapper;
    }

    private void getPeriodCached(Wrapper wrp) {
        if (wrp != null) {
            Period[] periods = wrp.getPeriods();
            for (Period period : periods) {
                Weather[] weathers = period.getWeathers();
                List<Weather> weatherList = Arrays.asList(weathers);
                cachePeriod.get().put(period.getValidTime(), weatherList);
            }
        }
    }


    // finding temperature data in a list given validTime param
    public Weather getWeatherData(String validTime) {

        Weather rspn = null;

        List<Weather> weatherList = cachePeriod.get().get(validTime);
        if (weatherList != null) {
            for (Weather wth : weatherList) {
                    if ( "t".equals(wth.getName()) ) {
                        rspn = wth;
                        return rspn;
                    }
            }
        } else {
            throw new java.lang.RuntimeException("Weather list is empty");
        }
        return rspn;
    }




}