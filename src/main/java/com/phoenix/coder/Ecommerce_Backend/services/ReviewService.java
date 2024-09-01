package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ReviewRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Review;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest request, UserModel user);
    List<Review> getProductReviews(Long productId);
}
