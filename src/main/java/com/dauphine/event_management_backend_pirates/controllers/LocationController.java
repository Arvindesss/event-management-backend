package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.LocationRequestBody;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.services.LocationService;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public ResponseEntity<Location> getLocationByValue(@RequestBody LocationRequestBody locationRequestBody)
            throws LocationNotFoundByIdException, LocationNotFoundByValueException {
        Location location = locationService.getByValue(locationRequestBody.address(), locationRequestBody.postalCode(),
                locationRequestBody.city(), locationRequestBody.country());
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable UUID id) throws LocationNotFoundByIdException {
        Location location = locationService.getById(id);
        return ResponseEntity.ok(location);
    }

    @PostMapping("")
    public ResponseEntity<Location> createLocation(@RequestBody LocationRequestBody locationRequestBody){
        Location location = locationService.create(locationRequestBody);
        return ResponseEntity
                .created(URI.create("v1/locations/" + location.getId()))
                .body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable UUID id, @RequestBody LocationRequestBody locationRequestBody)
            throws LocationNotFoundByIdException {
        Location location = locationService.update(id, locationRequestBody);
        return ResponseEntity
                .created(URI.create("v1/locations/" + location.getId()))
                .body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable UUID id) throws LocationNotFoundByIdException {
        locationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
