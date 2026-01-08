package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.DestinationDto;
import com.travelagency.voyageconnect1.domain.entity.Destination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    DestinationDto toDto(Destination destination);
    Destination toEntity(DestinationDto destinationDto);
}
