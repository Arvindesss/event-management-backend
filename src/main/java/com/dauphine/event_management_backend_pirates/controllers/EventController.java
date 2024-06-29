package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Event getEventById(@PathVariable UUID id){
        return eventService.getById(id);
    }

    @PostMapping("")
    public Event createEvent(@RequestBody CreateEventRequestBody createEventRequestBody){
        return eventService.create(createEventRequestBody);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable UUID id, @RequestBody String name){
        return eventService.update(id,name);
    }

    @DeleteMapping("/{id}")
    public UUID deleteEvent(@PathVariable UUID id){
        return eventService.deleteById(id)?id:null;
    }
}
