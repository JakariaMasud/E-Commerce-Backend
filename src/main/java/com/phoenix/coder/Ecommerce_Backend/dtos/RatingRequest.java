package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingRequest {
    private Long productId;
    private Double rating;
}
