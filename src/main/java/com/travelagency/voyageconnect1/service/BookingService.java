package com.travelagency.voyageconnect1.service;

import com.travelagency.voyageconnect1.domain.dto.BookingDto;
import java.util.Optional;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto);
    Optional<BookingDto> getBookingById(Long id);
    BookingDto confirmBooking(Long bookingId);
    BookingDto cancelBooking(Long bookingId);
}
