package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("select c from cart c where c.userId=:userId")
    Cart findByUserId(@Param("userId") Long userId);
}
