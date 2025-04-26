package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem> {
    CartItem findByCart_IdAndProductVariant_Id(Long cartId, Long variantId);
    List<CartItem> findByCart_Id(Long cartId);


}
