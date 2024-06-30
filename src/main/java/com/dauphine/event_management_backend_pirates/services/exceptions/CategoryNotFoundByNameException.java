package com.dauphine.event_management_backend_pirates.services.exceptions;

public class CategoryNotFoundByNameException extends Exception {
    public CategoryNotFoundByNameException(String msg) {
        super(msg);
    }
}
