package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("ENCODED PASSWORD: " + encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
