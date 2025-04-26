package com.phoenix.coder.Ecommerce_Backend.specifications;

import com.phoenix.coder.Ecommerce_Backend.models.Rating;
import org.springframework.data.jpa.domain.Specification;

public class RatingSpecification {
    public static Specification<Rating> hasProductId(Long productId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product").get("id"), productId);
    }
}
