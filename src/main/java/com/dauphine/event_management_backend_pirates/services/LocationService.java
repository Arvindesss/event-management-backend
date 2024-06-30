package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateLocationRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateLocationRequestBody;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByValueException;

import java.util.UUID;

public interface LocationService {

    Location getById(UUID id) throws LocationNotFoundByIdException;

    Location getByValue(String address, String postalCode, String city, String country) throws LocationNotFoundByIdException, LocationNotFoundByValueException;

    Location create(CreateLocationRequestBody createLocationRequestBody);

    Location update(UpdateLocationRequestBody updateLocationRequestBody) throws LocationNotFoundByIdException;

    void deleteById(UUID id) throws LocationNotFoundByIdException;
}
