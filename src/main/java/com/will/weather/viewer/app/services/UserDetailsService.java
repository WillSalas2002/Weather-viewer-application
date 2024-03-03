package com.will.weather.viewer.app.services;

import com.will.weather.viewer.app.models.User;
import com.will.weather.viewer.app.repository.UserRepository;
import com.will.weather.viewer.app.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(s);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new PersonDetails(userOptional.get());
    }
}
