package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.DestinationDto;
import com.travelagency.voyageconnect1.domain.entity.Destination;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class DestinationMapperImpl implements DestinationMapper {

    @Override
    public DestinationDto toDto(Destination destination) {
        if ( destination == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String country = null;
        String description = null;

        id = destination.getId();
        name = destination.getName();
        country = destination.getCountry();
        description = destination.getDescription();

        DestinationDto destinationDto = new DestinationDto( id, name, country, description );

        return destinationDto;
    }

    @Override
    public Destination toEntity(DestinationDto destinationDto) {
        if ( destinationDto == null ) {
            return null;
        }

        Destination destination = new Destination();

        destination.setId( destinationDto.id() );
        destination.setName( destinationDto.name() );
        destination.setCountry( destinationDto.country() );
        destination.setDescription( destinationDto.description() );

        return destination;
    }
}
