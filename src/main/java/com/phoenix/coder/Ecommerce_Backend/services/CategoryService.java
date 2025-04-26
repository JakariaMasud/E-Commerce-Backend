package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Category;

import java.util.Optional;

public interface CategoryService {
    public Optional<Category> findByNameAndParentName(String name, String parentName);
}
