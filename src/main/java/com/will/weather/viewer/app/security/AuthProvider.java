package com.will.weather.viewer.app.security;

import com.will.weather.viewer.app.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class AuthProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        String password = authentication.getCredentials().toString();

        if (userDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("Incorrect password or username");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
