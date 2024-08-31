package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCart(Long userId,Long cartId,CartItem cartItem);
    CartItem isCartItemExist(Cart cart, Product product,String size,Long userId);
    Void removeCartItem(Long cartItemId,Long userId);
    CartItem findCartItemById(Long cartItemId);
}
