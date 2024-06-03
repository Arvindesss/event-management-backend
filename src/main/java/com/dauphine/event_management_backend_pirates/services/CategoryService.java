package com.dauphine.event_management_backend_pirates.services;

import com.dauphine.event_management_backend_pirates.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllByName(String name);

    Category getById(UUID id);

    Category create(String name);

    Category updateName(UUID id, String name);

    boolean deleteById(UUID id);
}
