package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.ApiResponse;
import com.phoenix.coder.Ecommerce_Backend.dtos.VariantRequest;
import com.phoenix.coder.Ecommerce_Backend.models.ProductVariant;
import com.phoenix.coder.Ecommerce_Backend.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/productVariant")
@RequiredArgsConstructor
public class AdminProductVariantController {
    private ProductVariantService productVariantService;

    @PostMapping("/create/{productId}/")
    ResponseEntity<ProductVariant> createVariant(@PathVariable long productId, @RequestBody VariantRequest variantRequest) {
        ProductVariant productVariant = productVariantService.addProductVariant(variantRequest,productId);
        return ResponseEntity.ok(productVariant);
    }

    @PutMapping("/update/{productVariantId}/")
    ResponseEntity<ProductVariant> updateVariant(@PathVariable long productVariantId, @RequestBody VariantRequest variantRequest) {
        ProductVariant productVariant = productVariantService.updateProductVariant(variantRequest,productVariantId);
        return ResponseEntity.ok(productVariant);
    }
    @DeleteMapping("/delete/{ProductVariantId}/")
    ResponseEntity<ApiResponse> deleteVariant(@PathVariable long ProductVariantId) {
        productVariantService.deleteProductVariant(ProductVariantId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Successfully Deleted Product Variant")
                .status(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
