package com.dauphine.event_management_backend_pirates.repository;

import com.dauphine.event_management_backend_pirates.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByName(String name);
}
