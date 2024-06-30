package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.LocationRequestBody;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByValueException;

import java.util.UUID;

public interface LocationService {

    Location getById(UUID id) throws LocationNotFoundByIdException;

    Location getByValue(String address, String postalCode, String city, String country) throws LocationNotFoundByIdException, LocationNotFoundByValueException;

    Location create(LocationRequestBody locationRequestBody);

    Location update(UUID id, LocationRequestBody updateLocationRequestBody) throws LocationNotFoundByIdException;


    void deleteById(UUID id) throws LocationNotFoundByIdException;
}
