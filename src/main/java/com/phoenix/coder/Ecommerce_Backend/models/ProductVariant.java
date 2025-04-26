package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantId;

    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> attributes;
}

