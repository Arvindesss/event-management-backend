package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;

import java.util.List;
import java.util.UUID;


public interface EventService {
    List<Event> getAll();

    List<Event> getAllEventsToExplore(UUID userId);

    List<Event> getAllByName(String name);

    Event getById(UUID id) throws EventNotFoundByIdException;

    List<Event> getByOrganizerId(UUID userId) throws AppUserNotFoundByIdException;

    List<Event> getRegisteredEventsByUser(UUID userId) throws AppUserNotFoundByIdException;

    List<Event> getFinishedEventsByUser(UUID userId) throws AppUserNotFoundByIdException;

    Event create(CreateEventRequestBody createEventRequestBody) throws CategoryNotFoundByIdException, LocationNotFoundByIdException, AppUserNotFoundByIdException;

    Event update(UpdateEventRequestBody updateEventRequestBody) throws EventNotFoundByIdException, LocationNotFoundByIdException, CategoryNotFoundByIdException, AppUserNotFoundByIdException;

    void deleteById(UUID id) throws EventNotFoundByIdException;
}
