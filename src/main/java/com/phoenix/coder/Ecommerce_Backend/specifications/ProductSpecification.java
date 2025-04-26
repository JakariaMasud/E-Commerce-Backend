package com.phoenix.coder.Ecommerce_Backend.specifications;


import com.phoenix.coder.Ecommerce_Backend.models.Product;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("name"), category);
    }

    public static Specification<Product> priceBetween(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("discountedPrice"), minPrice, maxPrice);
    }

    public static Specification<Product> hasMinimumDiscount(Integer minDiscount) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDiscount);
    }

    public static Specification<Product> applySorting(String sort) {
        return (root, query, criteriaBuilder) -> {
            Order order;
            if ("price_low_to_high".equalsIgnoreCase(sort)) {
                order = criteriaBuilder.asc(root.get("discountedPrice"));
            } else if ("price_high_to_low".equalsIgnoreCase(sort)) {
                order = criteriaBuilder.desc(root.get("discountedPrice"));
            } else {
                order = criteriaBuilder.asc(root.get("id"));
            }
            query.orderBy(order);
            return null;
        };
    }
}
