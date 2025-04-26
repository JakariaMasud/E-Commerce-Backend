package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;

import java.util.List;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCart(Long userId,Long cartId,CartItem cartItem);
    Void removeCartItem(Long cartItemId,Long userId);
    CartItem findCartItemById(Long cartItemId);
    CartItem findByCartAndProductVariant(Long cartId, Long productVariantId);
    List<CartItem> findCartItemsByCartId(Long cartId);
}
