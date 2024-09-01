package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewRequest {
    private Long productId;
    private String review;

}
