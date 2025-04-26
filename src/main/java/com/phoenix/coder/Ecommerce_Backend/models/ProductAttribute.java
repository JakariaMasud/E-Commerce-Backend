package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeId;
    private String name;
    private String value;
}
