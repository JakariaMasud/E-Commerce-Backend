package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Boolean status;
}
