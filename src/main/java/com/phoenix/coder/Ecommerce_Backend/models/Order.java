package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    @ManyToOne
    private UserModel user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address shippingAddress;
    private Double totalPrice;
    private Integer totalDiscountedPrice;
    private String orderStatus;
    private  Integer totalItems;



}
