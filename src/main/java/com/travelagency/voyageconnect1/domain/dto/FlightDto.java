package com.travelagency.voyageconnect1.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightDto(
    Long id,
    String flightNumber,
    DestinationDto departureDestination,
    DestinationDto arrivalDestination,
    LocalDateTime departureTime,
    LocalDateTime arrivalTime,
    BigDecimal price,
    String airline,
    Integer availableSeats,
    String details
) {}
