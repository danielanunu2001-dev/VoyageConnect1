package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.entity.Circuit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DestinationMapper.class)
public interface CircuitMapper {
    CircuitDto toDto(Circuit circuit);
    Circuit toEntity(CircuitDto circuitDto);
}
