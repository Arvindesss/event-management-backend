package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateEventRequestBody(UUID eventId, String name, String description, LocalDateTime startDate, LocalDateTime endDate,
                                     UUID organizerId, UUID locationId, UUID categoryId) {
}
