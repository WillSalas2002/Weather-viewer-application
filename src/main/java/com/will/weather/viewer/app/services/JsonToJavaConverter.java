package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.dto.LocationDTO;
import com.will.weather.viewer.app.weatherEntities.WeatherResponse;
import com.will.weather.viewer.app.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    public LocationDTO convertToLocation(WeatherResponse weatherResponse) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setNames(weatherResponse.getName() + ", " + weatherResponse.getSys().getCountry());
        locationDTO.setLatitude(weatherResponse.getCoordinate().getLat());
        locationDTO.setLongitude(weatherResponse.getCoordinate().getLon());

        return locationDTO;
    }

    private static int convertKelvinToCelsius(double kelvin) {
        return (int) (kelvin - 273.15);
    }

    private static LocalDateTime convertEpochToLocalDateTime(long epoch) {
        Instant instant = Instant.ofEpochSecond(epoch);

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        return localDateTime;
    }
}
