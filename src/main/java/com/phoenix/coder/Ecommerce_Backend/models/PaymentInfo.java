package com.phoenix.coder.Ecommerce_Backend.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    private String cardHolderName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
}
