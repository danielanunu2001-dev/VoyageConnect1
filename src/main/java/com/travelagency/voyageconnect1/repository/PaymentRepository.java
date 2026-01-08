package com.travelagency.voyageconnect1.repository;

import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
}
