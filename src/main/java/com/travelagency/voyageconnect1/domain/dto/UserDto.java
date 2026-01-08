package com.travelagency.voyageconnect1.domain.dto;

import java.util.Set;

public record UserDto(
    Long id,
    String username,
    String email,
    Set<String> roles
) {}
