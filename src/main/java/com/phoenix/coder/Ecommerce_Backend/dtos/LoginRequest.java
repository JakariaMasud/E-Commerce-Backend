package com.phoenix.coder.Ecommerce_Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
