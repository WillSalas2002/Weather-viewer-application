package com.will.weather.viewer.app.weatherEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Coordinate {
    private BigDecimal lon;
    private BigDecimal lat;

}
