package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ReviewRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.Review;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class ReviewServiceImpl implements ReviewService {
    private ProductService productService;
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequest request, UserModel user) {
        Product product = productService.getProductById(request.getProductId());
        Review review = new Review();
        review.setReview(review.getReview());
        review.setUser(user);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getProductReviews(Long productId) {
        return reviewRepository.getProductRatings(productId);
    }
}
