package com.will.weather.viewer.app.controllers;

import com.will.weather.viewer.app.dto.LocationDTO;
import com.will.weather.viewer.app.dto.WeatherDTO;
import com.will.weather.viewer.app.models.Location;
import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.security.PersonDetails;
import com.will.weather.viewer.app.services.LocationService;
import com.will.weather.viewer.app.services.WeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final WeatherApiClient weatherApiClient;
    private final LocationService locationService;

    @Autowired
    public HomeController(WeatherApiClient weatherApiClient, LocationService locationService) {
        this.weatherApiClient = weatherApiClient;
        this.locationService = locationService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        Set<Location> userLocations = user.getLocations();
        List<WeatherDTO> weatherDTOS = userLocations.stream()
                .map(location -> weatherApiClient.sendRequestByLocation(location.getLatitude(), location.getLongitude()))
                .collect(Collectors.toList());

        model.addAttribute("weathers", weatherDTOS);

        return "home";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam("city-name") String cityName, Model model) {

        LocationDTO locationDTO = weatherApiClient.sendRequestByCityName(cityName);
        model.addAttribute("location", locationDTO);

        return "search";
    }

    @PostMapping("/add")
    public String addLocation(@ModelAttribute("location") LocationDTO locationDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        locationService.addLocationToUser(user, locationDTO);

        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("location_name") String name) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        Location location = locationService.findLocationByName(name);

        locationService.removeLocationFromUser(user, location);

        return "redirect:/home";
    }
}
