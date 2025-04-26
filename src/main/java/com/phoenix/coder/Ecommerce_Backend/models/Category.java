package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subcategories;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
