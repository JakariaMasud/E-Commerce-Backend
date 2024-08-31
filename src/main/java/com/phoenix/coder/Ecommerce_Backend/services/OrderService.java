package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.Address;
import com.phoenix.coder.Ecommerce_Backend.models.Order;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;

import java.util.List;

public interface OrderService {
    Order createOrder(UserModel user, Address shippingAddress);
    Order findOrderById(Long orderId);
    List<Order> getUserOrderHistory(Long userId);
    Order placeOrder(Long orderId);
    Order confirmOrder(Long orderId);
    Order shippedOrder(Long orderId);
    Order deliverOrder(Long orderId);
    Order cancelOrder(Long orderId);
    List<Order> getAllOrder();
    Void deleteOrder(Long orderId);
}
