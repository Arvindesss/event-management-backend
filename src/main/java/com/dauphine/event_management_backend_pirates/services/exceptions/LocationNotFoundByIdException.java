package com.dauphine.event_management_backend_pirates.services.exceptions;

public class LocationNotFoundByIdException extends Exception {

    public LocationNotFoundByIdException(String msg) {
        super(msg);
    }
}
