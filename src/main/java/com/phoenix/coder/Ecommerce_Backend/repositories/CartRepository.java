package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long>, JpaSpecificationExecutor<Cart> {
    Optional<Cart> findByUser_Id(Long userId);

}
