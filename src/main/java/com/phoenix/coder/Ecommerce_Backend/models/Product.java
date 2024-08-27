package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.jdbc.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Integer price;
    private Integer discountedPrice;
    private Integer discountPercent;
    @ElementCollection
    private Set<Size> sizes= new HashSet<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Rating>ratings;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> reviews;
    private int numRatings;
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDateTime createdAt;
    private String brand;
    private String color;
    private Integer quantity;

}
