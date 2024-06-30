package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.util.UUID;

public record UpdateEventParticipationRequestBody(UUID userId, UUID eventId, String feedback, double rating) {
}
