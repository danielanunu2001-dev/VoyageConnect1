package com.travelagency.voyageconnect1.repository;

import com.travelagency.voyageconnect1.domain.entity.Booking;
import com.travelagency.voyageconnect1.domain.entity.Review;
import com.travelagency.voyageconnect1.domain.entity.Review.ProductType;
import com.travelagency.voyageconnect1.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findByBooking(Booking booking);
    List<Review> findByProductTypeAndProductId(ProductType productType, Long productId);
}
