package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateEventRequestBody(String name, String description, LocalDateTime startDate, LocalDateTime endDate,
                                     UUID locationId, UUID categoryId, UUID organizerId) {
}
