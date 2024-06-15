package com.dauphine.event_management_backend_pirates.auth.services;

import com.dauphine.event_management_backend_pirates.auth.exceptions.UsernameAlreadyExistsException;
import com.dauphine.event_management_backend_pirates.auth.controller.requestbody.SignInDto;
import com.dauphine.event_management_backend_pirates.auth.controller.requestbody.SignUpDto;
import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AppUserRepository appUserRepository;

    private AuthenticationManager authenticationManager;


    public AuthService(AppUserRepository appUserRepository, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
    }

    public UserDetails signUp(SignUpDto data) throws UsernameAlreadyExistsException {
        if (appUserRepository.findByUsername(data.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        AppUser newUser = new AppUser(data.username(), encryptedPassword);
        return appUserRepository.save(newUser);
    }

    public AppUser authenticate(SignInDto data) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(), data.password()));
        return appUserRepository.findByUsername(data.username()).orElseThrow();
    }
}
