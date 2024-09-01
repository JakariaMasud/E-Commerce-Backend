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
    ResponseEntity<Page<Product>> getProducts(@RequestParam String category, @RequestParam List<String> sizes,
                                              @RequestParam List<String> colors, @RequestParam Integer minPrice,
                                              @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
                                              @RequestParam String sort, @RequestParam String stock,
                                              @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Product> products = productService.getProducts(category, colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
