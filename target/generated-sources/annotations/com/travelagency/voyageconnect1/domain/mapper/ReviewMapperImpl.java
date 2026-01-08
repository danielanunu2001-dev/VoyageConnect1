package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.ReviewDto;
import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Review;
import com.travelagency.voyageconnect1.domain.entity.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto toDto(Review review) {
        if ( review == null ) {
            return null;
        }

        Long userId = null;
        Long bookingId = null;
        Long id = null;
        Review.ProductType productType = null;
        Long productId = null;
        Integer rating = null;
        String comment = null;
        LocalDateTime reviewDate = null;

        userId = reviewUserId( review );
        bookingId = reviewBookingId( review );
        id = review.getId();
        productType = review.getProductType();
        productId = review.getProductId();
        rating = review.getRating();
        comment = review.getComment();
        reviewDate = review.getReviewDate();

        ReviewDto reviewDto = new ReviewDto( id, userId, bookingId, productType, productId, rating, comment, reviewDate );

        return reviewDto;
    }

    @Override
    public Review toEntity(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setUser( reviewDtoToUser( reviewDto ) );
        review.setBooking( reviewDtoToBooking( reviewDto ) );
        review.setId( reviewDto.id() );
        review.setProductType( reviewDto.productType() );
        review.setProductId( reviewDto.productId() );
        review.setRating( reviewDto.rating() );
        review.setComment( reviewDto.comment() );
        review.setReviewDate( reviewDto.reviewDate() );

        return review;
    }

    private Long reviewUserId(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long reviewBookingId(Review review) {
        if ( review == null ) {
            return null;
        }
        Booking booking = review.getBooking();
        if ( booking == null ) {
            return null;
        }
        Long id = booking.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User reviewDtoToUser(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( reviewDto.userId() );

        return user;
    }

    protected Booking reviewDtoToBooking(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( reviewDto.bookingId() );

        return booking;
    }
}
