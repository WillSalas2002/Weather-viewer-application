package com.will.weather.viewer.app.weatherEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class WeatherResponse {
    @JsonProperty("coord")
    private Coordinate coordinate;
    @JsonProperty("weather")
    private List<Weather> weathers;
    private Main main;
    private Wind wind;
    private long dt;
    private Sys sys;
    private String name;
}
