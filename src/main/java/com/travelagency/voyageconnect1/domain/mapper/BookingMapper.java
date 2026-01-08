package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.BookingDto;
import com.travelagency.voyageconnect1.domain.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, HotelMapper.class, CircuitMapper.class})
public interface BookingMapper {

    @Mapping(target = "userId", source = "user.id")
    BookingDto toDto(Booking booking);

    @Mapping(target = "user.id", source = "userId")
    Booking toEntity(BookingDto bookingDto);
}
