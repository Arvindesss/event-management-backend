package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.models.Location;

import java.util.UUID;

public interface LocationService {

    Location getById(UUID id);

    Location getByValue(String address, String postalCode, String city, String country);

    Location create(String address, String postalCode, String city, String country);

    boolean deleteById(UUID id);
}
