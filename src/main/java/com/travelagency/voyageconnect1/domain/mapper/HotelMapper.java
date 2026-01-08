package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import com.travelagency.voyageconnect1.domain.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DestinationMapper.class)
public interface HotelMapper {
    HotelDto toDto(Hotel hotel);
    Hotel toEntity(HotelDto hotelDto);
}
