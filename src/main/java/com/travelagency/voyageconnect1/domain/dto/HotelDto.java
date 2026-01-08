package com.travelagency.voyageconnect1.domain.dto;

import java.math.BigDecimal;

public record HotelDto(
    Long id,
    String name,
    DestinationDto destination,
    Integer rating,
    BigDecimal pricePerNight,
    Integer availableRooms,
    String details
) {}
