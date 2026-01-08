package com.travelagency.voyageconnect1.service;

import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TravelSearchService {
    Page<FlightDto> findFlights(Long departureDestinationId, Long arrivalDestinationId, LocalDate departureDate, Pageable pageable);
    Page<HotelDto> findHotels(Long destinationId, LocalDate checkInDate, LocalDate checkOutDate, Pageable pageable);
    Page<CircuitDto> findCircuits(Long destinationId, Pageable pageable);
}
