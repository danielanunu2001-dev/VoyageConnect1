package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.PaymentDto;
import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDto toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Long bookingId = null;
        Long id = null;
        BigDecimal amount = null;
        LocalDateTime paymentDate = null;
        Payment.PaymentStatus status = null;
        String paymentGatewayTransactionId = null;

        bookingId = paymentBookingId( payment );
        id = payment.getId();
        amount = payment.getAmount();
        paymentDate = payment.getPaymentDate();
        status = payment.getStatus();
        paymentGatewayTransactionId = payment.getPaymentGatewayTransactionId();

        PaymentDto paymentDto = new PaymentDto( id, bookingId, amount, paymentDate, status, paymentGatewayTransactionId );

        return paymentDto;
    }

    @Override
    public Payment toEntity(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setBooking( paymentDtoToBooking( paymentDto ) );
        payment.setId( paymentDto.id() );
        payment.setAmount( paymentDto.amount() );
        payment.setPaymentDate( paymentDto.paymentDate() );
        payment.setStatus( paymentDto.status() );
        payment.setPaymentGatewayTransactionId( paymentDto.paymentGatewayTransactionId() );

        return payment;
    }

    private Long paymentBookingId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Booking booking = payment.getBooking();
        if ( booking == null ) {
            return null;
        }
        Long id = booking.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Booking paymentDtoToBooking(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( paymentDto.bookingId() );

        return booking;
    }
}
