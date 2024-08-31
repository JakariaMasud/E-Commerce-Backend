package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.CartItemRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    @Override
    public Cart createCart(UserModel user) {
        return null;
    }

    @Override
    public Cart addCartItem(Long userId, CartItemRequest request) {
        return null;
    }

    @Override
    public Cart findUserCart(Long userid) {
        return null;
    }
}
