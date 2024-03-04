package com.will.weather.viewer.app.weatherEntities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WeatherDTO {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String main;
    private String description;
    private String icon;
    private int temp;
    private int tempFeelsLike;
    private int tempMin;
    private int tempMax;
    private int pressure;
    private int humidity;
    private double speed;
    private double windDegree;
    private double gust;
    private LocalDateTime currentTime;
    private String countryCode;
    private LocalDateTime sunriseTime;
    private LocalDateTime sunsetTime;
    private String cityName;

}
