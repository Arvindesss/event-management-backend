package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateLocationRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateLocationRequestBody;
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
    public ResponseEntity<Location> getLocationByValue(@RequestBody CreateLocationRequestBody createLocationRequestBody)
            throws LocationNotFoundByIdException, LocationNotFoundByValueException {
        Location location = locationService.getByValue(createLocationRequestBody.address(), createLocationRequestBody.postalCode(),
                createLocationRequestBody.city(), createLocationRequestBody.country());
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable UUID id) throws LocationNotFoundByIdException {
        Location location = locationService.getById(id);
        return ResponseEntity.ok(location);
    }

    @PostMapping("")
    public ResponseEntity<Location> createLocation(@RequestBody CreateLocationRequestBody createLocationRequestBody){
        Location location = locationService.create(createLocationRequestBody);
        return ResponseEntity
                .created(URI.create("v1/locations/" + location.getId()))
                .body(location);
    }

    @PutMapping("")
    public ResponseEntity<Location> updateLocation(@RequestBody UpdateLocationRequestBody updateLocationRequestBody)
            throws LocationNotFoundByIdException {
        Location location = locationService.update(updateLocationRequestBody);
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
