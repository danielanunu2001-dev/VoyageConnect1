package com.travelagency.voyageconnect1.repository;

import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Booking.BookingStatus;
import com.travelagency.voyageconnect1.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByStatus(BookingStatus status);
}
