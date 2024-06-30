package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventParticipationIdRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventParticipationRequestBody;
import com.dauphine.event_management_backend_pirates.models.AppUser;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.EventParticipation;
import com.dauphine.event_management_backend_pirates.models.EventParticipationId;
import com.dauphine.event_management_backend_pirates.repository.EventParticipationRepository;
import com.dauphine.event_management_backend_pirates.services.AppUserService;
import com.dauphine.event_management_backend_pirates.services.EventParticipationService;
import com.dauphine.event_management_backend_pirates.services.EventService;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventParticipationNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class EventParticipationServiceImpl implements EventParticipationService {

    private final EventParticipationRepository eventParticipationRepository;

    private final AppUserService appUserService;

    private final EventService eventService;

    public EventParticipationServiceImpl(EventParticipationRepository eventParticipationRepository,
                                         AppUserService appUserService, EventService eventService) {
        this.eventParticipationRepository = eventParticipationRepository;
        this.appUserService = appUserService;
        this.eventService = eventService;
    }

    @Override
    public List<EventParticipation> getAll() {
        return eventParticipationRepository.findAll();
    }

    @Override
    public EventParticipation getById(UUID userId, UUID eventId)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException, EventParticipationNotFoundException {
        AppUser appUser = appUserService.getById(userId);
        Event event = eventService.getById(eventId);
        EventParticipationId id = new EventParticipationId(appUser,event);
        return eventParticipationRepository.findById(id).orElseThrow(() ->
                new EventParticipationNotFoundException("EventParticipation with user id "
                + id.getUser().getId() + " and event id " + id.getEvent().getId() + " not found"));
    }

    @Override
    public List<EventParticipation> getByEventId(UUID eventId) throws EventNotFoundByIdException {
        eventService.getById(eventId);
        return eventParticipationRepository.findByEventId(eventId);
    }

    @Override
    public EventParticipation create(EventParticipationIdRequestBody eventParticipationIdRequestBody)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException {
        AppUser appUser = appUserService.getById(eventParticipationIdRequestBody.userId());
        Event event = eventService.getById(eventParticipationIdRequestBody.eventId());
        EventParticipationId id = new EventParticipationId(appUser,event);
        EventParticipation eventParticipation = new EventParticipation(id);
        return eventParticipationRepository.save(eventParticipation);
    }

    @Override
    public EventParticipation update(UUID userId, UUID eventId,
                                     UpdateEventParticipationRequestBody updateEventParticipationRequestBody)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException, EventParticipationNotFoundException {
       EventParticipation eventParticipation = getById(userId, eventId);
       eventParticipation.setFeedback(updateEventParticipationRequestBody.feedback());
       eventParticipation.setRating(updateEventParticipationRequestBody.rating());
        return eventParticipationRepository.save(eventParticipation);
    }

    @Override
    public void deleteById(UUID userId, UUID eventId)
            throws EventParticipationNotFoundException, EventNotFoundByIdException, AppUserNotFoundByIdException {
        EventParticipation eventParticipation = getById(userId, eventId);
        eventParticipationRepository.deleteById(eventParticipation.getId());
    }
}
