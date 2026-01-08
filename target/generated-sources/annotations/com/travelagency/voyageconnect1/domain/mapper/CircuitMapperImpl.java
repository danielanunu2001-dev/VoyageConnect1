package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.dto.DestinationDto;
import com.travelagency.voyageconnect1.domain.entity.Circuit;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class CircuitMapperImpl implements CircuitMapper {

    @Autowired
    private DestinationMapper destinationMapper;

    @Override
    public CircuitDto toDto(Circuit circuit) {
        if ( circuit == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        DestinationDto destination = null;
        Integer durationDays = null;
        BigDecimal price = null;
        String itinerary = null;
        Integer availableSlots = null;
        String details = null;

        id = circuit.getId();
        name = circuit.getName();
        destination = destinationMapper.toDto( circuit.getDestination() );
        durationDays = circuit.getDurationDays();
        price = circuit.getPrice();
        itinerary = circuit.getItinerary();
        availableSlots = circuit.getAvailableSlots();
        details = circuit.getDetails();

        CircuitDto circuitDto = new CircuitDto( id, name, destination, durationDays, price, itinerary, availableSlots, details );

        return circuitDto;
    }

    @Override
    public Circuit toEntity(CircuitDto circuitDto) {
        if ( circuitDto == null ) {
            return null;
        }

        Circuit circuit = new Circuit();

        circuit.setId( circuitDto.id() );
        circuit.setName( circuitDto.name() );
        circuit.setDestination( destinationMapper.toEntity( circuitDto.destination() ) );
        circuit.setDurationDays( circuitDto.durationDays() );
        circuit.setPrice( circuitDto.price() );
        circuit.setItinerary( circuitDto.itinerary() );
        circuit.setAvailableSlots( circuitDto.availableSlots() );
        circuit.setDetails( circuitDto.details() );

        return circuit;
    }
}
