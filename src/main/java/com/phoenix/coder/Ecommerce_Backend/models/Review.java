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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String review;
    @ManyToOne
    @JsonBackReference
    private Product product;
    @ManyToOne
    @JsonBackReference
    private UserModel user;
    private LocalDateTime createdAt;
}
