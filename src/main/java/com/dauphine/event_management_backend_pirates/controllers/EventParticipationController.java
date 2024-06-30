package com.dauphine.event_management_backend_pirates.controllers;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventParticipationIdRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventParticipationRequestBody;
import com.dauphine.event_management_backend_pirates.models.EventParticipation;
import com.dauphine.event_management_backend_pirates.services.EventParticipationService;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventParticipationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/events-participations")
public class EventParticipationController {

    private final EventParticipationService eventParticipationService;

    public EventParticipationController(EventParticipationService eventParticipationService) {
        this.eventParticipationService = eventParticipationService;
    }

    @GetMapping("")
    public ResponseEntity<List<EventParticipation>> getAll() {
        List<EventParticipation> eventParticipation = eventParticipationService.getAll();
        return ResponseEntity.ok(eventParticipation);
    }

    @GetMapping("/{userId}/{eventId}")
    public ResponseEntity<EventParticipation> getById(@PathVariable UUID userId, @PathVariable UUID eventId)
            throws EventNotFoundByIdException, AppUserNotFoundByIdException, EventParticipationNotFoundException {
        EventParticipation eventParticipation = eventParticipationService.getById(userId, eventId);
        return ResponseEntity.ok(eventParticipation);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<EventParticipation>> getByEventId(@PathVariable UUID eventId)
            throws EventNotFoundByIdException {
        List<EventParticipation> eventParticipations = eventParticipationService.getByEventId(eventId);
        return ResponseEntity.ok(eventParticipations);
    }

    @PostMapping("")
    public ResponseEntity<EventParticipation> create(@RequestBody EventParticipationIdRequestBody eventParticipationIdRequestBody) throws EventNotFoundByIdException, AppUserNotFoundByIdException {
        EventParticipation eventParticipation = eventParticipationService.create(eventParticipationIdRequestBody);
        return ResponseEntity
                .created(URI.create("v1/event-participations"))
                .body(eventParticipation);
    }

    @PutMapping("")
    public ResponseEntity<EventParticipation> update(
            @RequestBody UpdateEventParticipationRequestBody updateEventParticipationRequestBody)
            throws EventParticipationNotFoundException, EventNotFoundByIdException, AppUserNotFoundByIdException {
        EventParticipation eventParticipation = eventParticipationService.update(updateEventParticipationRequestBody);
        return ResponseEntity
                .created(URI.create("v1/event-participations"))
                .body(eventParticipation);
    }

    @DeleteMapping("/{userId}/{eventId}")
    public ResponseEntity<?> delete(@PathVariable UUID userId, @PathVariable UUID eventId)
            throws EventParticipationNotFoundException, EventNotFoundByIdException, AppUserNotFoundByIdException {
        eventParticipationService.deleteById(userId, eventId);
        return ResponseEntity.ok().build();
    }
}
