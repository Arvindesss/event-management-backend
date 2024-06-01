package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.Location;
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

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
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
    public Event create(String name, String description, LocalDateTime startDate, LocalDateTime endDate,
                        UUID locationId, UUID categoryId) {
        Optional<Location> location = locationRepository.findById(locationId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(location.isPresent() && category.isPresent()) {
            Event event = new Event(name,description,startDate,endDate,location.get(),category.get());
            return eventRepository.save(event);
        }
        else throw new RuntimeException("Category or location not found");
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
