package com.travelagency.voyageconnect1.domain.dto;

public record DestinationDto(
    Long id,
    String name,
    String country,
    String description
) {}
