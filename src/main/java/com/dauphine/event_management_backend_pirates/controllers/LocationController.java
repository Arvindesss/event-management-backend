package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.LocationDataRequestBody;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public Location getLocationByValue(@RequestBody LocationDataRequestBody locationDataRequestBody){
        return locationService.getByValue(locationDataRequestBody.address(), locationDataRequestBody.postalCode(),
                locationDataRequestBody.city(),locationDataRequestBody.country());
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable UUID id){
        return locationService.getById(id);
    }

    @PostMapping("")
    public Location createLocation(@RequestBody LocationDataRequestBody locationDataRequestBody){
        return locationService.create(locationDataRequestBody.address(), locationDataRequestBody.postalCode(),
                locationDataRequestBody.city(),locationDataRequestBody.country());
    }

    @DeleteMapping("/{id}")
    public UUID deleteLocation(@PathVariable UUID id){
        return locationService.deleteById(id)?id:null;
    }
}
