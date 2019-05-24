package com.example.CrazyWeather;

import domain.Period;
import domain.Wrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.JSONParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static service.JSONParser.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyWeatherApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWeatherParser() throws IOException {
		List<Period> periods = getPeriod();
	}


}
