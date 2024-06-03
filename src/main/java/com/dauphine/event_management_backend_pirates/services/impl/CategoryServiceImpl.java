package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.repository.CategoryRepository;
import com.dauphine.event_management_backend_pirates.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if(category == null){
            return null;
        }
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
