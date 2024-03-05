package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.dto.LocationDTO;
import com.will.weather.viewer.app.models.Location;
import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class LocationService {
    private final LocationsRepository locationsRepository;

    @Autowired
    public LocationService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    public Optional<Location> findLocationByLonAndLat(BigDecimal lon, BigDecimal lat) {
        return locationsRepository.findByLongitudeAndLatitude(lon, lat);
    }

    public Location findLocationByName(String name) {
        return locationsRepository.findByName(name);
    }

    public void addLocationToUser(User user, LocationDTO locationDTO) {

        Optional<Location> locationOptional = findLocationByLonAndLat(locationDTO.getLongitude(), locationDTO.getLatitude());
        Location location;

        if (locationOptional.isPresent()) {
            location = locationOptional.get();
        } else {
            location = new Location(locationDTO.getNames(), locationDTO.getLatitude(), locationDTO.getLongitude());
            locationsRepository.save(location);
        }

        location.getUsers().add(user);
        user.getLocations().add(location);
        locationsRepository.save(location);
    }

    public void removeLocationFromUser(User user, Location location) {

        user.getLocations().remove(location);
        location.getUsers().remove(user);

        locationsRepository.save(location);
    }
}
