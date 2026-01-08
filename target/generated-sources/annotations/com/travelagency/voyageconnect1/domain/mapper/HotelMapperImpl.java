package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.DestinationDto;
import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import com.travelagency.voyageconnect1.domain.entity.Hotel;
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
public class HotelMapperImpl implements HotelMapper {

    @Autowired
    private DestinationMapper destinationMapper;

    @Override
    public HotelDto toDto(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        DestinationDto destination = null;
        Integer rating = null;
        BigDecimal pricePerNight = null;
        Integer availableRooms = null;
        String details = null;

        id = hotel.getId();
        name = hotel.getName();
        destination = destinationMapper.toDto( hotel.getDestination() );
        rating = hotel.getRating();
        pricePerNight = hotel.getPricePerNight();
        availableRooms = hotel.getAvailableRooms();
        details = hotel.getDetails();

        HotelDto hotelDto = new HotelDto( id, name, destination, rating, pricePerNight, availableRooms, details );

        return hotelDto;
    }

    @Override
    public Hotel toEntity(HotelDto hotelDto) {
        if ( hotelDto == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setId( hotelDto.id() );
        hotel.setName( hotelDto.name() );
        hotel.setDestination( destinationMapper.toEntity( hotelDto.destination() ) );
        hotel.setRating( hotelDto.rating() );
        hotel.setPricePerNight( hotelDto.pricePerNight() );
        hotel.setAvailableRooms( hotelDto.availableRooms() );
        hotel.setDetails( hotelDto.details() );

        return hotel;
    }
}
