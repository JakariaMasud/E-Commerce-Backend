package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Cart;
import com.phoenix.coder.Ecommerce_Backend.models.CartItem;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService{
    private UserModelService userService;
    private CartItemRepository cartItemRepository;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getPrice());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCart(Long userId, Long cartId, CartItem cartItem) {
        CartItem item= findCartItemById(cartItem.getId());
        UserModel user = userService.getUserById(userId);
        if(item.getUserId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getProduct().getPrice()* item.getQuantity());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()* item.getQuantity());
            return cartItemRepository.save(item);
        }

        return null;
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart,product,size,userId);
    }

    @Override
    public Void removeCartItem(Long cartItemId, Long userId) {
        CartItem cartItem =findCartItemById(cartItemId);
            UserModel user = userService.getUserById(cartItem.getUserId());
            UserModel reqUser = userService.getUserById(userId);
            if(user.equals(reqUser)){
                cartItemRepository.deleteById(cartItemId);
            }
        return null;
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        return null;
    }
}