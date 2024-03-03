package com.will.weather.viewer.app.controllers;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.services.RegistrationService;
import com.will.weather.viewer.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("user")User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user")User user) {
        return "login";
    }
}
