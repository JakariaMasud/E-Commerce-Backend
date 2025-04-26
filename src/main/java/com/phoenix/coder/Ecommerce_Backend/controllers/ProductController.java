package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @GetMapping("/")
    ResponseEntity<List<Product>> getProducts() {
        List<Product> productList=productService.getProducts();
        return new ResponseEntity<>( productList,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
