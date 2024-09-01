package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.RatingRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.RatingService;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {
    private RatingService ratingService;
    private UserModelService userModelService;

    @PostMapping("/rate")
    ResponseEntity<Rating> rateProduct(@RequestHeader("Authorization") String jwt, @RequestBody RatingRequest ratingRequest) {
        UserModel user = userModelService.findUserByJwt(jwt);
        Rating rating = ratingService.createRating(ratingRequest, user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    ResponseEntity<List<Rating>> getRatingsByProductId(@PathVariable Long productId) {
        List<Rating> ratings = ratingService.getProductRatings(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
