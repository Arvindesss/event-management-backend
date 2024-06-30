package com.dauphine.event_management_backend_pirates.controllers;


import com.dauphine.event_management_backend_pirates.services.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({
            AppUserNotFoundByIdException.class,
            CategoryNotFoundByIdException.class,
            CategoryNotFoundByNameException.class,
            EventNotFoundByIdException.class,
            EventParticipationNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex){
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
           CategoryAlreadyExistsException.class
    })
    public ResponseEntity<String> handleCategoryAlreadyExistsException(Exception ex){
        logger.warn("[ALREADY EXISTS] {}", ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }
}
