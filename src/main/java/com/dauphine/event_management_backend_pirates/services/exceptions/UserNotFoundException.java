package com.dauphine.event_management_backend_pirates.services.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
