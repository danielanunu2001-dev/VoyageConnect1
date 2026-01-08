package com.travelagency.voyageconnect1.domain.dto;

import com.travelagency.voyageconnect1.domain.entity.Review.ProductType;
import java.time.LocalDateTime;

public record ReviewDto(
    Long id,
    Long userId,
    Long bookingId,
    ProductType productType,
    Long productId,
    Integer rating,
    String comment,
    LocalDateTime reviewDate
) {}
