package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.ApiResponse;
import com.phoenix.coder.Ecommerce_Backend.dtos.ProductRequest;
import com.phoenix.coder.Ecommerce_Backend.dtos.VariantRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController{
    private ProductService productService;

    @GetMapping("/")
    ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest){
        Product savedProduct=productService.addProduct(productRequest);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    @DeleteMapping("/{productId}/delete")
    ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Successfully Deleted Product")
                .status(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}/")
    ResponseEntity<Product> updateProduct(@RequestBody ProductRequest request,@PathVariable Long productId){
        Product updatedProduct=productService.updateProduct(request,productId);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }
}
