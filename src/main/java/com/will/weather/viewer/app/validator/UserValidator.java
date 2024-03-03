package com.will.weather.viewer.app.validator;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        Optional<User> userOptional = usersService.findUserByLogin(user.getLogin());

        if (userOptional.isPresent()) {
            errors.rejectValue("login", "", "User with this login already exists, please enter another login");
        }
    }
}
