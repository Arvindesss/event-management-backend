package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.models.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> getAllUsers();
}
