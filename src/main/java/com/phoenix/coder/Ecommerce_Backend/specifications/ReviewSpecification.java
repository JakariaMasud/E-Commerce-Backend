package com.phoenix.coder.Ecommerce_Backend.specifications;

import com.phoenix.coder.Ecommerce_Backend.models.Review;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {
    public static Specification<Review> hasProductId(Long productId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product").get("id"), productId);
    }
}
