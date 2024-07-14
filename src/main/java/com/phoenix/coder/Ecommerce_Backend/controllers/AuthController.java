package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.Utils.JwtUtils;
import com.phoenix.coder.Ecommerce_Backend.dtos.LoginRequest;
import com.phoenix.coder.Ecommerce_Backend.dtos.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        LoginResponse response = new LoginResponse("Token : " + token, "Successfully logged in");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
