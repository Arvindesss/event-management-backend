package com.dauphine.event_management_backend_pirates.repository;

import com.dauphine.event_management_backend_pirates.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByName(String name);

    @Query("""
            SELECT category
            FROM Category category
            WHERE UPPER(category.name) LIKE UPPER(CONCAT('%', :name , '%'))
            """)
    List<Category> findAllLikeName(String name);

    Optional<Category> findByNameIgnoreCase(String name);
}
