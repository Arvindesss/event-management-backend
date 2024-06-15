package com.dauphine.event_management_backend_pirates.auth.controller.requestbody;

public record SignUpDto(
        String username,
        String password) {
}