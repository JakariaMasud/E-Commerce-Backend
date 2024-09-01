package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.ReviewRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Review;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.ReviewService;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private UserModelService userModelService;

    @PostMapping("/review")
    ResponseEntity<Review> rateProduct(@RequestHeader("Authorization") String jwt, @RequestBody ReviewRequest reviewRequest) {
        UserModel user = userModelService.findUserByJwt(jwt);
        Review review = reviewService.createReview(reviewRequest, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    ResponseEntity<List<Review>> getRatingsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getProductReviews(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
