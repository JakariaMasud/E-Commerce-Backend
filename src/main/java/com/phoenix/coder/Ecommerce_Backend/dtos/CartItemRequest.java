package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemRequest {
    private Long productVariantId;
    private Integer quantity;
    private Integer price;
    private Long userId;
}
