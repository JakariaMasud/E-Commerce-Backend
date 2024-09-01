package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.RatingRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequest request, UserModel user);
    List<Rating>getProductRatings(Long productId);
}
