package com.dauphine.event_management_backend_pirates.controllers.requestbody;

public record CreateLocationRequestBody(String address, String postalCode, String city, String country) {
}
