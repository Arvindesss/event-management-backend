package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public record EventParticipationIdRequestBody(UUID userId, UUID eventId) {
}
