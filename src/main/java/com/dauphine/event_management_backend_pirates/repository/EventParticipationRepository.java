package com.dauphine.event_management_backend_pirates.repository;

import com.dauphine.event_management_backend_pirates.models.Event;
import com.dauphine.event_management_backend_pirates.models.EventParticipation;
import com.dauphine.event_management_backend_pirates.models.EventParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventParticipationRepository extends JpaRepository<EventParticipation, EventParticipationId>{

    @Query(value = """
            SELECT *
            FROM event_participation ep
            WHERE ep.eventId = :eventId
            """, nativeQuery = true)
    List<EventParticipation> findByEventId(UUID eventId);
}
