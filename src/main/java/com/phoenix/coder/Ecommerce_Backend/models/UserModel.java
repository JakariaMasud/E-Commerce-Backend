package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Address> addresses;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Rating> ratings;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Review> reviews;
    @CreatedDate
    private LocalDate createdAt;
    private String role;

}
