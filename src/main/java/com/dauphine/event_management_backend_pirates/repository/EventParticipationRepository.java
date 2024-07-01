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
            SELECT ep
            FROM EventParticipation ep
            WHERE ep.id.event.id = :eventId
            AND ep.feedback IS NOT NULL AND ep.rating IS NOT NULL
            """)
    List<EventParticipation> findByEventId(UUID eventId);
}
