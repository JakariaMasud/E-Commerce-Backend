package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Rating>ratings;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> reviews;
    private String imgUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariant> variants;

}
