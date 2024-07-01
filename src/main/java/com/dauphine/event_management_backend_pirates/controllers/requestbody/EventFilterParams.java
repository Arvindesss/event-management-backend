package com.dauphine.event_management_backend_pirates.controllers.requestbody;

import java.time.LocalDateTime;

public record EventFilterParams(String name, Boolean isAscendingOrder, LocalDateTime startDateInterval, LocalDateTime endDateInterval,
                                String location, String categoryId) {}