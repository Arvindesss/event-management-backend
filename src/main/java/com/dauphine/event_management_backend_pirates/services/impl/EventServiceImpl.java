package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.Location;
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
    @Override
    public Event create(CreateEventRequestBody createEventRequestBody)
            throws CategoryNotFoundByIdException, LocationNotFoundByIdException, AppUserNotFoundByIdException {
        Location location = locationService.getById(createEventRequestBody.locationId());
        Category category = categoryService.getById(createEventRequestBody.categoryId());
        AppUser user = appUserService.getById(createEventRequestBody.organizerId());
        Event event = new Event(createEventRequestBody.name(),
                createEventRequestBody.description(),
                createEventRequestBody.startDate(),
                createEventRequestBody.endDate(),
                location,
                category,
                user);
        return eventRepository.save(event);
    }

    @Override
    public Event update(UpdateEventRequestBody updateEventRequestBody) throws EventNotFoundByIdException, LocationNotFoundByIdException, CategoryNotFoundByIdException, AppUserNotFoundByIdException {
        Event event = getById(updateEventRequestBody.eventId());
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
