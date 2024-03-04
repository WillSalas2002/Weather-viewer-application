package com.will.weather.viewer.app.weatherEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
    private double speed;
    private double degree;
    private double gust;
}
