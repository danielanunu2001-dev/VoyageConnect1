package com.travelagency.voyageconnect1.domain.dto;

import java.math.BigDecimal;

public record CircuitDto(
    Long id,
    String name,
    DestinationDto destination,
    Integer durationDays,
    BigDecimal price,
    String itinerary,
    Integer availableSlots,
    String details
) {}
