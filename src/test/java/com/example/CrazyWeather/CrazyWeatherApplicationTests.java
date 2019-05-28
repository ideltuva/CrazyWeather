package com.example.CrazyWeather;

import domain.Period;
import domain.Weather;
import domain.Wrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.JSONParserClient;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyWeatherApplicationTests {

	JSONParserClient jsonClient = new JSONParserClient();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWrapper() {
		Wrapper wrp = jsonClient.getWrapper();
		if (wrp != null) {
			Period[] periods = wrp.getPeriods();
			for (Period prd : periods) {
				String validTime = prd.getValidTime();
				System.out.println(validTime);
			}
		}
	}


	@Test
	public void testWeatherDataOnDate() {
		Weather weather = jsonClient.getWeatherData("2019-05-17T10:00:00Z");
		if (weather != null ) System.out.println("Temperature on 05.16: " + Double.toString(weather.getValue()));
	}


}
