package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Size {
    private String name;
    private Integer quantity;
}
