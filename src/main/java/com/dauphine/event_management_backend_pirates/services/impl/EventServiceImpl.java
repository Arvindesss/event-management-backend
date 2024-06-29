package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.Location;
import com.dauphine.event_management_backend_pirates.repository.AppUserRepository;
import com.dauphine.event_management_backend_pirates.repository.CategoryRepository;
import com.dauphine.event_management_backend_pirates.repository.EventRepository;
import com.dauphine.event_management_backend_pirates.repository.LocationRepository;
import com.dauphine.event_management_backend_pirates.services.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final CategoryRepository categoryRepository;

    private final LocationRepository locationRepository;

    private final AppUserRepository appUserRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository,
                            LocationRepository locationRepository, AppUserRepository appUserRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.appUserRepository = appUserRepository;
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
    public Event getById(UUID id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event create(CreateEventRequestBody createEventRequestBody) {
        Optional<Location> location = locationRepository.findById(createEventRequestBody.locationId());
        Optional<Category> category = categoryRepository.findById(createEventRequestBody.categoryId());
        Optional<AppUser> user = appUserRepository.findById(createEventRequestBody.organizerId());
        if(location.isPresent() && category.isPresent() && user.isPresent()) {
            Event event = new Event(createEventRequestBody.name(),
                    createEventRequestBody.description(),
                    createEventRequestBody.startDate(),
                    createEventRequestBody.endDate(),
                    location.get(),
                    category.get(),
                    user.get());
            return eventRepository.save(event);
        }
        else throw new RuntimeException("Category or location or user not found");
    }

    @Override
    public Event update(UUID id, String name) {
        //TODO: todo later
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        eventRepository.deleteById(id);
        return true;
    }
}
