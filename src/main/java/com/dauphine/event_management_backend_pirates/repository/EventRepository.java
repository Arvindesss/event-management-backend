package com.dauphine.event_management_backend_pirates.repository;

import com.dauphine.event_management_backend_pirates.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByName(String name);

    @Query("""
            SELECT e FROM Event e
            LEFT JOIN EventParticipation ep ON e.id = ep.id.event.id AND ep.id.user.id = :userId
            WHERE e.organizer.id != :userId
            AND (ep.id IS NULL OR ep.id.user.id <> :userId)
            AND e.startDate >= CURRENT_TIMESTAMP
            """)
    List<Event> findAllToExplore(UUID userId);

    @Query("""
            SELECT e FROM Event e
            INNER JOIN EventParticipation ep ON ep.id.event.id = e.id
            WHERE ep.id.user.id = :userId
            """)
    List<Event> findAllUserRegisteredEvents(UUID userId);

    @Query("""
            SELECT e FROM Event e
            WHERE e.organizer.id = :organizerId
            """)
    List<Event> findAllByOrganizerId(UUID organizerId);

    @Query("""
            SELECT e FROM Event e
            INNER JOIN EventParticipation ep ON ep.id.event.id = e.id
            WHERE ep.id.user.id = :userId
            AND e.organizer.id != :userId
            AND e.endDate < CURRENT_TIMESTAMP
            """)
    List<Event> findAllFinishedEvents(UUID userId);
}
