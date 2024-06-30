package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.util.UUID;

public record UpdateEventParticipationRequestBody(String feedback, Double rating) {
}
