package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from review r where r.product.id=:productId")
    List<Review> getProductRatings(@Param("productId") Long productId);
}
