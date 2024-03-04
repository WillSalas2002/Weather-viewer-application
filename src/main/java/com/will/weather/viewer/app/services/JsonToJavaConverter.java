package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.weatherEntities.WeatherResponse;
import com.will.weather.viewer.app.weatherEntities.WeatherDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class JsonToJavaConverter {

    public WeatherDTO convert(WeatherResponse weatherResponse) {

        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setMain(weatherResponse.getWeathers().get(0).getMain());
        weatherDTO.setDescription(weatherResponse.getWeathers().get(0).getDescription());
        weatherDTO.setIcon(weatherResponse.getWeathers().get(0).getIcon());
        weatherDTO.setTemp(convertKelvinToCelsius(weatherResponse.getMain().getTemp()));
        weatherDTO.setTempFeelsLike(convertKelvinToCelsius(weatherResponse.getMain().getFeelsLike()));
        weatherDTO.setTempMin(convertKelvinToCelsius(weatherResponse.getMain().getTempMin()));
        weatherDTO.setTempMax(convertKelvinToCelsius(weatherResponse.getMain().getTempMax()));
        weatherDTO.setPressure(weatherResponse.getMain().getPressure());
        weatherDTO.setHumidity(weatherResponse.getMain().getHumidity());
        weatherDTO.setSpeed(weatherResponse.getWind().getSpeed());
        weatherDTO.setWindDegree(weatherResponse.getWind().getDegree());
        weatherDTO.setGust(weatherResponse.getWind().getGust());
        weatherDTO.setSunriseTime(convertEpochToLocalDateTime(weatherResponse.getSys().getSunrise()));
        weatherDTO.setSunsetTime(convertEpochToLocalDateTime(weatherResponse.getSys().getSunset()));
        weatherDTO.setCurrentTime(convertEpochToLocalDateTime(weatherResponse.getDt()));
        weatherDTO.setCityName(weatherResponse.getName());
        weatherDTO.setCountryCode(weatherResponse.getSys().getCountry());

        return weatherDTO;
    }

    private static int convertKelvinToCelsius(double kelvin) {
        System.out.println(kelvin);
        return (int) (kelvin - 273.15);
    }

    private static LocalDateTime convertEpochToLocalDateTime(long epoch) {
        Instant instant = Instant.ofEpochSecond(epoch);

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        return localDateTime;
    }
}
