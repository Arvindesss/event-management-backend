package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.util.UUID;

public record UpdateLocationRequestBody(UUID locationId, String address, String postalCode, String city, String country) {
}
