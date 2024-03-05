package com.will.weather.viewer.app.repository;

import com.will.weather.viewer.app.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Integer> {

    Optional<Location> findByLongitudeAndLatitude(BigDecimal lon, BigDecimal lat);

    Location findByName(String name);
}
