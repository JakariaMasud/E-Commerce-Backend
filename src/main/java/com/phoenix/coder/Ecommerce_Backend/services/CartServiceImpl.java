package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.CartItemRequest;
import com.phoenix.coder.Ecommerce_Backend.models.*;
import com.phoenix.coder.Ecommerce_Backend.repositories.CartItemRepository;
import com.phoenix.coder.Ecommerce_Backend.repositories.CartRepository;
import com.phoenix.coder.Ecommerce_Backend.repositories.ProductVariantRepository;
import com.phoenix.coder.Ecommerce_Backend.specifications.CartSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final ProductVariantService productVariantService;
    private CartItemService cartItemService;
    private UserModelService userModelService;
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Long userId) {
        UserModel user=userModelService.getUserById(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(Long userId, CartItemRequest request) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductVariant variant = productVariantService.getProductVariant(request.getProductVariantId());

        CartItem cartItem = cartItemService.findByCartAndProductVariant(cart.getId(), request.getProductVariantId());

        if (cartItem != null) {
            // Update existing cart item
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setPrice(variant.getPrice().intValue() * cartItem.getQuantity());
        } else {
            // Create new cart item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductVariant(variant);
            cartItem.setQuantity(1);
            cartItem.setPrice(variant.getPrice().intValue() * request.getQuantity());
            cartItem.setUserId(userId);
        }

        cartItemService.createCartItem(cartItem);

        // Update cart totals
        Set<CartItem> items = cartItemService.findCartItemsByCartId(cart.getId()).stream()
                .filter(ci -> ci.getCart().getId().equals(cart.getId()))
                .collect(Collectors.toSet());
        cart.setCartItems(items);
        cart.setTotalItems(items.size());
        cart.setTotalPrice(items.stream().mapToInt(CartItem::getPrice).sum());
        cart.setTotalDiscount(0);

        return cartRepository.save(cart);
    }

    @Override
    public Cart findUserCart(Long userid) {
        Specification<Cart> spec = CartSpecifications.hasUserId(userid);
        Cart cart= cartRepository.findOne(spec).orElse(null);
        if(cart==null) createCart(userid);
        Integer totalPrice = 0;
        Integer totalItems=0;
        for(CartItem item: cart.getCartItems()){
            totalPrice+=item.getPrice();
            totalItems+=item.getQuantity();
        }
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
}
