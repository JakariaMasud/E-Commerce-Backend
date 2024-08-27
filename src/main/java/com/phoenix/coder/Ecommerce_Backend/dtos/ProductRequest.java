package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String title;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
}
