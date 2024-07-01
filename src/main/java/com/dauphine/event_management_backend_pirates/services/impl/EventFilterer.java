package com.dauphine.event_management_backend_pirates.services.impl;

import com.dauphine.event_management_backend_pirates.controllers.requestbody.EventFilterParams;
import com.dauphine.event_management_backend_pirates.models.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventFilterer {

    public static List<Event> sort(List<Event> events, EventFilterParams filterParams){
        return events.stream()
                .filter(event -> filterParams.name() == null || event.getName().toLowerCase().contains(filterParams.name().toLowerCase()))
                .filter(event -> filterParams.startDateInterval() == null || !event.getStartDate().isBefore(filterParams.startDateInterval()))
                .filter(event -> filterParams.endDateInterval() == null || !event.getStartDate().isAfter(filterParams.endDateInterval()))
                .filter(event -> filterParams.location() == null || event.getLocation().toLocationString().toLowerCase().contains(filterParams.location().toLowerCase()))
                .filter(event -> filterParams.categoryId() == null || event.getCategory().getId().toString().equals(filterParams.categoryId()))
                .sorted((e1, e2) -> {
                    if (filterParams.isAscendingOrder() != null && filterParams.isAscendingOrder()) {
                        return e1.getStartDate().compareTo(e2.getStartDate());
                    } else {
                        return e2.getStartDate().compareTo(e1.getStartDate());
                    }
                })
                .collect(Collectors.toList());
    }
}
