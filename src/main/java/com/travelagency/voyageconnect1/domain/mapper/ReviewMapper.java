package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.ReviewDto;
import com.travelagency.voyageconnect1.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "bookingId", source = "booking.id")
    ReviewDto toDto(Review review);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "booking.id", source = "bookingId")
    Review toEntity(ReviewDto reviewDto);
}
