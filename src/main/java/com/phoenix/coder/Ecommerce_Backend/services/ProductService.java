package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ProductRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductRequest product);
    List<Product> getProducts();
    Product getProduct(Long id);
    void deleteProduct(Long id);
    Product updateProduct(ProductRequest request, Long productId);

}
