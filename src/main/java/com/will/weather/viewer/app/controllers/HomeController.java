package com.will.weather.viewer.app.controllers;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(@ModelAttribute("user") User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getUser().getLogin());
        System.out.println(personDetails.getUser().getPassword());
        return "home";
    }
}
