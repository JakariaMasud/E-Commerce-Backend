package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.CartItemRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    @Override
    public Cart createCart(UserModel user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addCartItem(Long userId, CartItemRequest request) {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(request.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart,product, request.getSize(), userId);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setUserId(userId);
            cartItem.setQuantity(request.getQuantity());
            Integer price= product.getPrice()* request.getQuantity();
            cartItem.setPrice(price);
            Integer discountedPrice= product.getDiscountedPrice()* request.getQuantity();
            cartItem.setDiscountedPrice(discountedPrice);
            cartItem.setSize(request.getSize());
            CartItem createdCartItem= cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return null;
    }

    @Override
    public Cart findUserCart(Long userid) {
        Cart cart = cartRepository.findByUserId(userid);
        Integer totalPrice = 0;
        Integer totalDiscountedPrice=0;
        Integer totalItems=0;
        for(CartItem item: cart.getCartItems()){
            totalPrice+=item.getPrice();
            totalDiscountedPrice+=item.getDiscountedPrice();
            totalItems+=item.getQuantity();
        }
        cart.setTotalItems(totalItems);
        cart.setTotalDiscount(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
}
