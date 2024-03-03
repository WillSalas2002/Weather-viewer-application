package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
