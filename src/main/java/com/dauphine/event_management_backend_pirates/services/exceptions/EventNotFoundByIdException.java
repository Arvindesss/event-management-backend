package com.dauphine.event_management_backend_pirates.services.exceptions;

public class EventNotFoundByIdException extends Exception {
    public EventNotFoundByIdException(String msg) {
        super(msg);
    }
}
