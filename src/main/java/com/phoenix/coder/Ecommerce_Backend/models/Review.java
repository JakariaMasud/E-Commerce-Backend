package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    private Product product;
    @ManyToOne
    private UserModel user;
    @CreatedDate
    private LocalDateTime createdAt;
}
