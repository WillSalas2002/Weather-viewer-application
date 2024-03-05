package com.will.weather.viewer.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WeatherDTO {
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
