package com.travelagency.voyageconnect1.domain.dto;

import com.travelagency.voyageconnect1.domain.entity.Booking.BookingStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record BookingDto(
    Long id,
    Long userId,
    LocalDateTime bookingDate,
    BookingStatus status,
    BigDecimal totalPrice,
    Set<FlightDto> flights,
    Set<HotelDto> hotels,
    Set<CircuitDto> circuits
) {}
