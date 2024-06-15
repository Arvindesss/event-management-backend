package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("v1/users")
@RestController
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/authenticated-user")
    public ResponseEntity<AppUser> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("")
    public ResponseEntity<List<AppUser>> allUsers() {
        List <AppUser> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
