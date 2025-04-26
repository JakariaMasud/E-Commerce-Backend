package com.phoenix.coder.Ecommerce_Backend.specifications;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CartItemSpecifications {
    public static Specification<CartItem> isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (cart != null) {
                predicates.add(criteriaBuilder.equal(root.get("cart"), cart));
            }
            if (product != null) {
                predicates.add(criteriaBuilder.equal(root.get("product"), product));
            }
            if (size != null) {
                predicates.add(criteriaBuilder.equal(root.get("size"), size));
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
