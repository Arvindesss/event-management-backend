package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventRequestBody;
import com.dauphine.event_management_backend_pirates.models.*;
import com.dauphine.event_management_backend_pirates.repository.EventRepository;
import com.dauphine.event_management_backend_pirates.services.AppUserService;
import com.dauphine.event_management_backend_pirates.services.CategoryService;
import com.dauphine.event_management_backend_pirates.services.EventService;
import com.dauphine.event_management_backend_pirates.services.LocationService;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.LocationNotFoundByIdException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final CategoryService categoryService;

    private final LocationService locationService;

    private final AppUserService appUserService;

    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService,
                            LocationService locationService, AppUserService appUserService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.appUserService = appUserService;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsToExplore(UUID userId) {
        return eventRepository.findAllToExplore(userId);
    }

    @Override
    public List<Event> getAllByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public Event getById(UUID id) throws EventNotFoundByIdException {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException("Event with id "
                + id + " not found"));
    }

    public List<Event> getByOrganizerId(UUID organizerId) throws AppUserNotFoundByIdException {
        appUserService.getById(organizerId);
        return eventRepository.findAllByOrganizerId(organizerId);
    }

    @Override
    public List<Event> getRegisteredEventsByUser(UUID userId) throws AppUserNotFoundByIdException {
        appUserService.getById(userId);
        return eventRepository.findAllUserRegisteredEvents(userId);
    }

    @Override
    public List<Event> getFinishedEventsByUser(UUID userId) throws AppUserNotFoundByIdException {
        appUserService.getById(userId);
        return eventRepository.findAllFinishedEvents(userId);
    }
    @Override
    public Event create(EventRequestBody eventRequestBody)
            throws CategoryNotFoundByIdException, LocationNotFoundByIdException, AppUserNotFoundByIdException {
        Location location = locationService.getById(eventRequestBody.locationId());
        Category category = categoryService.getById(eventRequestBody.categoryId());
        AppUser user = appUserService.getById(eventRequestBody.organizerId());
        Event event = new Event(eventRequestBody.name(),
                eventRequestBody.description(),
                eventRequestBody.startDate(),
                eventRequestBody.endDate(),
                location,
                category,
                user);
        return eventRepository.save(event);
    }

    @Override
    public Event update(UUID id, EventRequestBody updateEventRequestBody) throws EventNotFoundByIdException, LocationNotFoundByIdException, CategoryNotFoundByIdException, AppUserNotFoundByIdException {
        Event event = getById(id);
        event.setName(updateEventRequestBody.name());
        event.setDescription(updateEventRequestBody.description());
        event.setStartDate(updateEventRequestBody.startDate());
        event.setEndDate(updateEventRequestBody.endDate());
        event.setLocation(locationService.getById(updateEventRequestBody.locationId()));
        event.setCategory(categoryService.getById(updateEventRequestBody.categoryId()));
        event.setOrganizer(appUserService.getById(updateEventRequestBody.organizerId()));
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(UUID id) throws EventNotFoundByIdException {
        getById(id);
        eventRepository.deleteById(id);
    }
}
