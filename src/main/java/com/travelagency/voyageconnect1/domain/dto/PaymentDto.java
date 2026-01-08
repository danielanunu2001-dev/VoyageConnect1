package com.travelagency.voyageconnect1.domain.dto;

import com.travelagency.voyageconnect1.domain.entity.Payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(
    Long id,
    Long bookingId,
    BigDecimal amount,
    LocalDateTime paymentDate,
    PaymentStatus status,
    String paymentGatewayTransactionId
) {}
