package com.dauphine.event_management_backend_pirates.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "event_participation")
public class EventParticipation {

    @EmbeddedId
    private EventParticipationId id;
    @Column(name = "feedback")
    private String feedback;

    @Column(name = "rating")
    private Double rating;

    public EventParticipation(EventParticipationId id, String feedback, Double rating) {
        this.id = id;
        this.feedback = feedback;
        this.rating = rating;
    }

    public EventParticipation() {
    }

    public EventParticipationId getId() {
        return id;
    }

    public void setId(EventParticipationId id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
