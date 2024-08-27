package com.phoenix.coder.Ecommerce_Backend.controllers;

import com.phoenix.coder.Ecommerce_Backend.Utils.JwtUtils;
import com.phoenix.coder.Ecommerce_Backend.dtos.LoginRequest;
import com.phoenix.coder.Ecommerce_Backend.dtos.AuthResponse;
import com.phoenix.coder.Ecommerce_Backend.dtos.SignupRequest;
import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    PasswordEncoder passwordEncoder;
    UserModelService userModelService;

    @PostMapping("/signup")
    ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) {
        //validate all fields
        UserModel user = new UserModel();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        UserModel savedUser = userModelService.createUser(user);
        Authentication authToken = UsernamePasswordAuthenticationToken.unauthenticated(savedUser.getEmail(), savedUser.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        AuthResponse authResponse = new AuthResponse(jwtToken, "Successfully Registration Completed");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        AuthResponse response = new AuthResponse(token, "Successfully logged in");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
