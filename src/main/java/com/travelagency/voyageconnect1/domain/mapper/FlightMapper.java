package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DestinationMapper.class)
public interface FlightMapper {
    FlightDto toDto(Flight flight);
    Flight toEntity(FlightDto flightDto);
}
