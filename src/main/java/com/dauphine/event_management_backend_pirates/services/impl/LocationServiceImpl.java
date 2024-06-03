package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.repository.LocationRepository;
import com.dauphine.event_management_backend_pirates.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getById(UUID id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location getByValue(String address, String postalCode, String city, String country) {
        return locationRepository.findByAddressAndPostalCodeAndCityAndCountry(address, postalCode, city, country)
                .orElse(null);
    }

    @Override
    public Location create(String address, String postalCode, String city, String country) {
        Optional<Location> existingLocation = locationRepository.findByAddressAndPostalCodeAndCityAndCountry(address, postalCode, city, country);
        if(existingLocation.isPresent()) {
            return existingLocation.get();
        }
        Location location = new Location(address, postalCode, city, country);
        return locationRepository.save(location);
    }

    @Override
    public boolean deleteById(UUID id) {
        locationRepository.deleteById(id);
        return true;
    }
}
