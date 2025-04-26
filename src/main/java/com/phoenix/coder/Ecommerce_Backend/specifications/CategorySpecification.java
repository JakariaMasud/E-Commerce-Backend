package com.phoenix.coder.Ecommerce_Backend.specifications;

import com.phoenix.coder.Ecommerce_Backend.models.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Category> hasParentName(String parentName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("parentCategory").get("name"), parentName);
    }
}
