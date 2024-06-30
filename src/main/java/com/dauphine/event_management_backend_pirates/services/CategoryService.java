package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryAlreadyExistsException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_management_backend_pirates.services.exceptions.CategoryNotFoundByNameException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllLikeName(String name);

    Category getById(UUID id) throws CategoryNotFoundByIdException;

    Category getByNameIgnoreCase(String name) throws CategoryNotFoundByNameException;

    Category create(String name) throws CategoryAlreadyExistsException;

    Category updateName(UUID id, String name) throws CategoryNotFoundByIdException;

    void deleteById(UUID id) throws CategoryNotFoundByIdException;
}
