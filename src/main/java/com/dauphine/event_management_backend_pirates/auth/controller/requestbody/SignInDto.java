package com.dauphine.event_management_backend_pirates.auth.controller.requestbody;

public record SignInDto(
        String username,
        String password) {
}
