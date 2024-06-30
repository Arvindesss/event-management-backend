package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.repository.AppUserRepository;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements com.dauphine.event_management_backend_pirates.services.AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getById(UUID id) throws AppUserNotFoundByIdException {
        return appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundByIdException("App user with id "
                + id + " not found"));
    }
}
