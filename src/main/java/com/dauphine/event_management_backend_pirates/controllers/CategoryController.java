package com.dauphine.event_management_backend_pirates.controllers;


import com.dauphine.event_management_backend_pirates.models.Category;
import com.dauphine.event_management_backend_pirates.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategories(@RequestParam(required = false) String name){
        return name == null || name.isBlank() ? categoryService.getAll() : categoryService.getAllByName(name);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable UUID id){
        return categoryService.getById(id);
    }

    @PostMapping("")
    public Category createCategory(@RequestBody String name){
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategoryName(@PathVariable UUID id, @RequestBody String name){
        return categoryService.updateName(id,name);
    }

    @DeleteMapping("/{id}")
    public UUID deleteCategory(@PathVariable UUID id){
        return categoryService.deleteById(id)?id:null;
    }
}
