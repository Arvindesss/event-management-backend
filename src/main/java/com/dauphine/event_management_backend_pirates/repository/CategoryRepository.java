package com.dauphine.event_management_backend_pirates.repository;

import com.dauphine.event_management_backend_pirates.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByName(String name);
}
