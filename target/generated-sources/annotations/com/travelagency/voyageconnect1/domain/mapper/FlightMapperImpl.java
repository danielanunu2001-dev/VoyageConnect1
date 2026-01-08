package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.DestinationDto;
import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.entity.Flight;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Autowired
    private DestinationMapper destinationMapper;

    @Override
    public FlightDto toDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        Long id = null;
        String flightNumber = null;
        DestinationDto departureDestination = null;
        DestinationDto arrivalDestination = null;
        LocalDateTime departureTime = null;
        LocalDateTime arrivalTime = null;
        BigDecimal price = null;
        String airline = null;
        Integer availableSeats = null;
        String details = null;

        id = flight.getId();
        flightNumber = flight.getFlightNumber();
        departureDestination = destinationMapper.toDto( flight.getDepartureDestination() );
        arrivalDestination = destinationMapper.toDto( flight.getArrivalDestination() );
        departureTime = flight.getDepartureTime();
        arrivalTime = flight.getArrivalTime();
        price = flight.getPrice();
        airline = flight.getAirline();
        availableSeats = flight.getAvailableSeats();
        details = flight.getDetails();

        FlightDto flightDto = new FlightDto( id, flightNumber, departureDestination, arrivalDestination, departureTime, arrivalTime, price, airline, availableSeats, details );

        return flightDto;
    }

    @Override
    public Flight toEntity(FlightDto flightDto) {
        if ( flightDto == null ) {
            return null;
        }

        Flight flight = new Flight();

        flight.setId( flightDto.id() );
        flight.setFlightNumber( flightDto.flightNumber() );
        flight.setDepartureDestination( destinationMapper.toEntity( flightDto.departureDestination() ) );
        flight.setArrivalDestination( destinationMapper.toEntity( flightDto.arrivalDestination() ) );
        flight.setDepartureTime( flightDto.departureTime() );
        flight.setArrivalTime( flightDto.arrivalTime() );
        flight.setPrice( flightDto.price() );
        flight.setAirline( flightDto.airline() );
        flight.setAvailableSeats( flightDto.availableSeats() );
        flight.setDetails( flightDto.details() );

        return flight;
    }
}
