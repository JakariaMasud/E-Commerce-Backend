package com.phoenix.coder.Ecommerce_Backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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
    @JsonManagedReference
    List<Address> addresses;
    @ElementCollection
    @CollectionTable(name = "payment_info",joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInfo>paymentInfos;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Rating> ratings;
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Review> reviews;
    @CreatedDate
    private LocalDate createdAt;
    private String role;

}
