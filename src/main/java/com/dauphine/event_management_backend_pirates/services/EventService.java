package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface EventService {
    List<Event> getAll();

    List<Event> getAllByName(String name);

    Event getById(UUID id);

    Event create(CreateEventRequestBody createEventRequestBody);

    Event update(UUID id, String name);

    boolean deleteById(UUID id);

    List<Event> getAllEventsToExplore(UUID userId);
}
