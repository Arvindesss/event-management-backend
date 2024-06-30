package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    List<AppUser> getAllUsers();

    AppUser getById(UUID id) throws AppUserNotFoundByIdException;
}
