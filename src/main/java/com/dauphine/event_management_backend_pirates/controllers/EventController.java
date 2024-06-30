package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.EventParticipation;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.services.EventService;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/events")
public class  EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public List<Event> getAllEvents(@RequestParam(required = false) String name){
        return name == null || name.isBlank() ? eventService.getAll() : eventService.getAllByName(name);
    }

    @GetMapping("explore/{userId}")
    public ResponseEntity<List<Event>> getAllEventsToExplore(@PathVariable UUID userId){
        List<Event> events = eventService.getAllEventsToExplore(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event>  getEventById(@PathVariable UUID id) throws EventNotFoundByIdException {
        Event event = eventService.getById(id);
        return ResponseEntity.ok(event);
    }
    @GetMapping("/my-events/{organizerId}")
    public ResponseEntity<List<Event>> getByOrganizerId(@PathVariable UUID organizerId)
            throws AppUserNotFoundByIdException {
        List<Event> events = eventService.getByOrganizerId(organizerId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/registered-events/{userId}")
    public ResponseEntity<List<Event>> getRegisteredEventsByUser(@PathVariable UUID userId)
            throws AppUserNotFoundByIdException {
        List<Event> events = eventService.getRegisteredEventsByUser(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/finished-events/{userId}")
    public ResponseEntity<List<Event>> getFinishedEventsByUser(@PathVariable UUID userId)
            throws AppUserNotFoundByIdException {
        List<Event> events = eventService.getFinishedEventsByUser(userId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("")
    public ResponseEntity<Event>  createEvent(@RequestBody CreateEventRequestBody createEventRequestBody)
            throws CategoryNotFoundByIdException, LocationNotFoundByIdException, AppUserNotFoundByIdException {
        Event event = eventService.create(createEventRequestBody);
        return ResponseEntity
                .created(URI.create("v1/events/" + event.getId()))
                .body(event);
    }

    @PutMapping("")
    public ResponseEntity<Event> updateEvent(@RequestBody UpdateEventRequestBody updateEventRequestBody)
            throws CategoryNotFoundByIdException, LocationNotFoundByIdException, EventNotFoundByIdException,
            AppUserNotFoundByIdException {
        Event event = eventService.update(updateEventRequestBody);
        return ResponseEntity
                .created(URI.create("v1/events/" + event.getId()))
                .body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
