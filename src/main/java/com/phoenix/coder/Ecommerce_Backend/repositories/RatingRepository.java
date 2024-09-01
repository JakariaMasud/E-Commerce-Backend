package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("select r from ratings r where r.product.id=:productId")
    List<Rating> getProductRatings(@Param("productId") Long productId);
}
