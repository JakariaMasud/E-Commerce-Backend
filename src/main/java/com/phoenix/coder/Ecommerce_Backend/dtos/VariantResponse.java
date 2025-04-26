package com.phoenix.coder.Ecommerce_Backend.dtos;

import java.math.BigDecimal;
import java.util.List;

public class VariantResponse {
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private List<AttributeResponse> attributes;
}
