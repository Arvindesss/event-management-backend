package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.LocationRequestBody;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.repository.LocationRepository;
import com.dauphine.event_management_backend_pirates.services.LocationService;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByValueException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getById(UUID id) throws LocationNotFoundByIdException {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundByIdException("Location with id "
                + id + " not found"));
    }

    @Override
    public Location getByValue(String address, String postalCode, String city, String country) throws LocationNotFoundByValueException {
        return locationRepository.findByAddressAndPostalCodeAndCityAndCountry(address, postalCode, city, country)
                .orElseThrow(() -> new LocationNotFoundByValueException("Location with value: " + address + "," +
                        " " + postalCode + ", " + city + ", " + country +  " not found"));
    }

    @Override
    public Location create(LocationRequestBody locationRequestBody) {
        Location location = new Location(
                locationRequestBody.address(),
                locationRequestBody.postalCode(),
                locationRequestBody.city(),
                locationRequestBody.country());
        return locationRepository.save(location);
    }

    @Override
    public Location update(UUID id, LocationRequestBody updateLocationRequestBody) throws LocationNotFoundByIdException {
        Location location = getById(id);
        location.setAddress(updateLocationRequestBody.address());
        location.setPostalCode(updateLocationRequestBody.postalCode());
        location.setCity(updateLocationRequestBody.city());
        location.setCountry(updateLocationRequestBody.country());
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(UUID id) throws LocationNotFoundByIdException {
        getById(id);
        locationRepository.deleteById(id);
    }
}
