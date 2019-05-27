package com.example.CrazyWeather;

import domain.Period;
import domain.Wrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static service.JSONParser.getWrapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyWeatherApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWrapper() throws IOException {
		Wrapper wrp = getWrapper();
		if (wrp != null) {
			Period[] periods = wrp.getPeriods();
			for (Period prd:periods) {
				String validTime = prd.getValidTime();
				System.out.println(validTime);
			}
		}



	}


}
