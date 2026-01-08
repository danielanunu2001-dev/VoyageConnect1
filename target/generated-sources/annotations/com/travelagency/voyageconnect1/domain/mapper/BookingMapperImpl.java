package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.BookingDto;
import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Circuit;
import com.travelagency.voyageconnect1.domain.entity.Flight;
import com.travelagency.voyageconnect1.domain.entity.Hotel;
import com.travelagency.voyageconnect1.domain.entity.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private CircuitMapper circuitMapper;

    @Override
    public BookingDto toDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        Long userId = null;
        Long id = null;
        LocalDateTime bookingDate = null;
        Booking.BookingStatus status = null;
        BigDecimal totalPrice = null;
        Set<FlightDto> flights = null;
        Set<HotelDto> hotels = null;
        Set<CircuitDto> circuits = null;

        userId = bookingUserId( booking );
        id = booking.getId();
        bookingDate = booking.getBookingDate();
        status = booking.getStatus();
        totalPrice = booking.getTotalPrice();
        flights = flightSetToFlightDtoSet( booking.getFlights() );
        hotels = hotelSetToHotelDtoSet( booking.getHotels() );
        circuits = circuitSetToCircuitDtoSet( booking.getCircuits() );

        BookingDto bookingDto = new BookingDto( id, userId, bookingDate, status, totalPrice, flights, hotels, circuits );

        return bookingDto;
    }

    @Override
    public Booking toEntity(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setUser( bookingDtoToUser( bookingDto ) );
        booking.setId( bookingDto.id() );
        booking.setBookingDate( bookingDto.bookingDate() );
        booking.setStatus( bookingDto.status() );
        booking.setTotalPrice( bookingDto.totalPrice() );
        booking.setFlights( flightDtoSetToFlightSet( bookingDto.flights() ) );
        booking.setHotels( hotelDtoSetToHotelSet( bookingDto.hotels() ) );
        booking.setCircuits( circuitDtoSetToCircuitSet( bookingDto.circuits() ) );

        return booking;
    }

    private Long bookingUserId(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        User user = booking.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<FlightDto> flightSetToFlightDtoSet(Set<Flight> set) {
        if ( set == null ) {
            return null;
        }

        Set<FlightDto> set1 = new LinkedHashSet<FlightDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Flight flight : set ) {
            set1.add( flightMapper.toDto( flight ) );
        }

        return set1;
    }

    protected Set<HotelDto> hotelSetToHotelDtoSet(Set<Hotel> set) {
        if ( set == null ) {
            return null;
        }

        Set<HotelDto> set1 = new LinkedHashSet<HotelDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Hotel hotel : set ) {
            set1.add( hotelMapper.toDto( hotel ) );
        }

        return set1;
    }

    protected Set<CircuitDto> circuitSetToCircuitDtoSet(Set<Circuit> set) {
        if ( set == null ) {
            return null;
        }

        Set<CircuitDto> set1 = new LinkedHashSet<CircuitDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Circuit circuit : set ) {
            set1.add( circuitMapper.toDto( circuit ) );
        }

        return set1;
    }

    protected User bookingDtoToUser(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( bookingDto.userId() );

        return user;
    }

    protected Set<Flight> flightDtoSetToFlightSet(Set<FlightDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Flight> set1 = new LinkedHashSet<Flight>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FlightDto flightDto : set ) {
            set1.add( flightMapper.toEntity( flightDto ) );
        }

        return set1;
    }

    protected Set<Hotel> hotelDtoSetToHotelSet(Set<HotelDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Hotel> set1 = new LinkedHashSet<Hotel>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( HotelDto hotelDto : set ) {
            set1.add( hotelMapper.toEntity( hotelDto ) );
        }

        return set1;
    }

    protected Set<Circuit> circuitDtoSetToCircuitSet(Set<CircuitDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Circuit> set1 = new LinkedHashSet<Circuit>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CircuitDto circuitDto : set ) {
            set1.add( circuitMapper.toEntity( circuitDto ) );
        }

        return set1;
    }
}
