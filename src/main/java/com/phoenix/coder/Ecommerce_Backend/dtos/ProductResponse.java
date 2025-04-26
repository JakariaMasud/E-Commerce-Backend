package com.phoenix.coder.Ecommerce_Backend.dtos;

import com.phoenix.coder.Ecommerce_Backend.models.ProductVariant;
import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import com.phoenix.coder.Ecommerce_Backend.models.Review;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class ProductResponse {

    private String title;
    private String description;
    private List<Rating> ratings;
    private List<Review> reviews;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<VariantResponse> variants;
}
