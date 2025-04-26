package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Category;
import com.phoenix.coder.Ecommerce_Backend.repositories.CategoryRepository;
import com.phoenix.coder.Ecommerce_Backend.specifications.CategorySpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findByNameAndParentName(String name, String parentName) {
        Specification<Category> spec = Specification
                .where(CategorySpecification.hasName(name))
                .and(CategorySpecification.hasParentName(parentName));
        return categoryRepository.findOne(spec);
    }
}
