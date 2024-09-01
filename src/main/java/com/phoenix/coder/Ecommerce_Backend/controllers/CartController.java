package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.CartItemRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.CartService;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;
    private UserModelService userModelService;

    @GetMapping("/")
    ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String jwt){
        UserModel user = userModelService.findUserByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PostMapping("/add")
    ResponseEntity<Cart> addItemToCart(@RequestHeader("Authorization") String jwt, CartItemRequest request){
        UserModel user = userModelService.findUserByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        Cart updatedCart=cartService.addCartItem(user.getId(),request);
        return new ResponseEntity<>(updatedCart,HttpStatus.CREATED);
    }
}
