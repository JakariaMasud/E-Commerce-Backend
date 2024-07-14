package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
    private int level;
}
