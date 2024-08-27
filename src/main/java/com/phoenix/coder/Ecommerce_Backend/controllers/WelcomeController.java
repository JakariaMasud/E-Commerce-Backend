package com.phoenix.coder.Ecommerce_Backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WelcomeController {

    @GetMapping("/welcome")
    String welcome() {
        return "Welcome To E-Commerce API";
    }

}
