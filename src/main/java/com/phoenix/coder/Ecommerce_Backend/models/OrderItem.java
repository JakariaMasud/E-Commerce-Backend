package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private ProductVariant productVariant;
    private Integer quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userId;
}
