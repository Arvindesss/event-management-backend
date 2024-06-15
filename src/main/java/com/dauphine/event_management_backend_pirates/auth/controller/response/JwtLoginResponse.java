package com.dauphine.event_management_backend_pirates.auth.controller.response;

public record JwtLoginResponse(String accessToken, long tokenExpiration) {
}