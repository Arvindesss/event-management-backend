package com.dauphine.event_management_backend_pirates.services.exceptions;

public class AppUserNotFoundByIdException extends Exception {

    public AppUserNotFoundByIdException(String msg) {
        super(msg);
    }
}
