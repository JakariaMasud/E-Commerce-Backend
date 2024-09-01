package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.dtos.ApiResponse;
import com.phoenix.coder.Ecommerce_Backend.models.Order;
import com.phoenix.coder.Ecommerce_Backend.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderController {
    private OrderService orderService;

    @GetMapping("/")
    ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId/confirm")
    ResponseEntity<Order> confirmOrder(@PathVariable Long orderId) {
        Order order = orderService.confirmOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId/ship")
    ResponseEntity<Order> shipOrder(@PathVariable Long orderId) {
        Order order = orderService.shipOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId/deliver")
    ResponseEntity<Order> deliverOrder(@PathVariable Long orderId) {
        Order order = orderService.deliverOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId/cancel")
    ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId/delete")
    ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) {
        orderService.deleteOrder(orderId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Successfully Deleted Order")
                .status(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
