package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public  class VariantRequest {
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private List<AttributeRequest> attributes;

}
