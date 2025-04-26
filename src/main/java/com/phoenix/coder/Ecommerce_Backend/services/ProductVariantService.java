package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.VariantRequest;
import com.phoenix.coder.Ecommerce_Backend.models.ProductVariant;

public interface ProductVariantService {
    ProductVariant addProductVariant(VariantRequest request, Long productId);

    ProductVariant updateProductVariant(VariantRequest request, Long variantId);

    ProductVariant getProductVariant(Long variant);

    void deleteProductVariant(Long variant);

}
