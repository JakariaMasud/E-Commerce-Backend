package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
