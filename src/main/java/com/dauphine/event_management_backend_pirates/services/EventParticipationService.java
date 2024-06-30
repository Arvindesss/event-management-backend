package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventParticipationIdRequestBody;
import com.dauphine.event_management_backend_pirates.controllers.requestbody.UpdateEventParticipationRequestBody;
import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.EventParticipation;
import com.dauphine.event_management_backend_pirates.models.EventParticipationId;
import com.dauphine.event_management_backend_pirates.services.exceptions.AppUserNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.EventParticipationNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventParticipationService {

    List<EventParticipation> getAll();

    EventParticipation getById(UUID userId, UUID eventId)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException, EventParticipationNotFoundException;

    List<EventParticipation> getByEventId(UUID eventId) throws EventNotFoundByIdException;

    EventParticipation create(EventParticipationIdRequestBody eventParticipationIdRequestBody)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException;

    EventParticipation update(UpdateEventParticipationRequestBody updateEventParticipationRequestBody)
            throws AppUserNotFoundByIdException, EventNotFoundByIdException, EventParticipationNotFoundException;

    void deleteById(UUID userId, UUID eventId)
            throws EventParticipationNotFoundException, EventNotFoundByIdException, AppUserNotFoundByIdException;
}
