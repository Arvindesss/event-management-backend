package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventFilterParams;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;

import java.util.List;
import java.util.UUID;


public interface EventService {
    List<Event> getAll();

    List<Event> getAllByName(String name);

    Event getById(UUID id) throws EventNotFoundByIdException;

    List<Event> getAllEventsToExplore(UUID userId, EventFilterParams eventFilterParams);

    List<Event> getByOrganizerId(UUID organizerId, EventFilterParams eventFilterParams) throws AppUserNotFoundByIdException;

    List<Event> getRegisteredEventsByUser(UUID userId, EventFilterParams eventFilterParams) throws AppUserNotFoundByIdException;

    List<Event> getFinishedEventsByUser(UUID userId, EventFilterParams eventFilterParams) throws AppUserNotFoundByIdException;

    Event create(EventRequestBody eventRequestBody) throws CategoryNotFoundByIdException, LocationNotFoundByIdException, AppUserNotFoundByIdException;

    Event update(UUID id, EventRequestBody updateEventRequestBody) throws EventNotFoundByIdException, LocationNotFoundByIdException, CategoryNotFoundByIdException, AppUserNotFoundByIdException;

    void deleteById(UUID id) throws EventNotFoundByIdException;
}
