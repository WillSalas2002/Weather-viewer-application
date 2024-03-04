package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.weatherEntities.WeatherDTO;
import com.will.weather.viewer.app.weatherEntities.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class WeatherApiClient {
    private static final String URL_BY_NAME = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String URL_API = "&appid=";
    private static final String URL_BY_LOCATION_LAT = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private static final String URL_BY_LOCATION_LON = "&lon=";
    private static final String API_KEY = "7c4b89b1b811d4a287d45d0fab5564ec";
    private final RestTemplate restTemplate;
    private final JsonToJavaConverter converter;

    @Autowired
    public WeatherApiClient(RestTemplate restTemplate, JsonToJavaConverter converter) {
        this.restTemplate = restTemplate;
        this.converter = converter;
    }

    public WeatherDTO sendRequestByCityName(String cityName) {
        String url = URL_BY_NAME + cityName + URL_API + API_KEY;
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        WeatherDTO weatherDTO = converter.convert(weatherResponse);
        return weatherDTO;
    }

    public WeatherDTO sendRequestByLocation(BigDecimal lat, BigDecimal lon) {
        String url = URL_BY_LOCATION_LAT + lat + URL_BY_LOCATION_LON + lon + URL_API + API_KEY;
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        WeatherDTO weatherDTO = converter.convert(weatherResponse);
        return weatherDTO;
    }
}
