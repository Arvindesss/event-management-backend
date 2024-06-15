package com.dauphine.event_management_backend_pirates.auth.controller;

import com.dauphine.event_management_backend_pirates.auth.services.AuthService;
import com.dauphine.event_management_backend_pirates.auth.services.JwtService;
import com.dauphine.event_management_backend_pirates.auth.exceptions.UsernameAlreadyExistsException;
import com.dauphine.event_management_backend_pirates.auth.controller.response.JwtLoginResponse;
import com.dauphine.event_management_backend_pirates.auth.controller.requestbody.SignInDto;
import com.dauphine.event_management_backend_pirates.auth.controller.requestbody.SignUpDto;
import com.dauphine.event_management_backend_pirates.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AppUserAuthController {

    private final JwtService jwtService;

    private final AuthService authService;

    public AppUserAuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtLoginResponse> signUp(@RequestBody SignUpDto data) {
        AppUser appUser = (AppUser) authService.signUp(data);
        String accessJwtToken = jwtService.generateToken(appUser);
        return ResponseEntity.ok(new JwtLoginResponse(accessJwtToken, jwtService.getExpirationTime()));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtLoginResponse> signIn(@RequestBody SignInDto data) {
        AppUser authenticatedUser = authService.authenticate(data);
        String accessJwtToken = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(new JwtLoginResponse(accessJwtToken, jwtService.getExpirationTime()));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
