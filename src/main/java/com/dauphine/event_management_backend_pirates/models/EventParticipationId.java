package com.dauphine.event_management_backend_pirates.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventParticipationId implements Serializable {

    @OneToOne
    @JoinColumn(name= "id_user")
    private AppUser user;

    @OneToOne
    @JoinColumn(name = "id_event")
    private Event event;

    public EventParticipationId(AppUser user, Event event) {
        this.user = user;
        this.event = event;
    }

    public EventParticipationId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventParticipationId that = (EventParticipationId) o;
        return Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
