package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.PaymentDto;
import com.travelagency.voyageconnect1.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "bookingId", source = "booking.id")
    PaymentDto toDto(Payment payment);

    @Mapping(target = "booking.id", source = "bookingId")
    Payment toEntity(PaymentDto paymentDto);
}
