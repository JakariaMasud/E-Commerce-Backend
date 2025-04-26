package com.phoenix.coder.Ecommerce_Backend.specifications;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecifications {
    public static Specification<Cart> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userId"), userId);
    }
}
