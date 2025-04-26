package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.RatingRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.repositories.RatingRepository;
import com.phoenix.coder.Ecommerce_Backend.specifications.RatingSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest request, UserModel user) {
        Product product= productService.getProductById(request.getProductId());
        Rating rating = new Rating();
        rating.setRating(request.getRating());
        rating.setProduct(product);
        rating.setUser(user);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRatings(Long productId) {
        Specification<Rating> spec = RatingSpecification.hasProductId(productId);
        return ratingRepository.findAll(spec);    }
}
