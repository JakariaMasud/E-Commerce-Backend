package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.CartItemRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;

public interface CartService {
    Cart createCart(UserModel user);
    Cart addCartItem(Long userId, CartItemRequest request);
    Cart findUserCart(Long userid);
}
