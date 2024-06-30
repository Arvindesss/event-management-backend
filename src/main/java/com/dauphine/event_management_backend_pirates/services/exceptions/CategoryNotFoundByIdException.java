package com.dauphine.event_management_backend_pirates.services.exceptions;

public class CategoryNotFoundByIdException extends Exception {
    public CategoryNotFoundByIdException(String msg) {
        super(msg);
    }
}
