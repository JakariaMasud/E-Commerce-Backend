package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JsonBackReference
    private UserModel user;
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
    private Integer totalPrice;
    private Integer totalItems;
    private Integer totalDiscountedPrice;
    private Integer totalDiscount;


}
