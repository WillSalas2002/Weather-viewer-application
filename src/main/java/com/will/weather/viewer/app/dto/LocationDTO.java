package com.will.weather.viewer.app.dto;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class LocationDTO {
    private String names;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public LocationDTO() {
    }

    public LocationDTO(String names, BigDecimal longitude, BigDecimal latitude) {
        this.names = names;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
