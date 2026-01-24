package com.finpro.auth_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finpro.auth_service.dto.LoginRequest;
import com.finpro.auth_service.dto.LoginResponse;
import com.finpro.auth_service.dto.RegisterUserRequest;
import com.finpro.auth_service.entity.User;
import com.finpro.auth_service.service.AuthService;
import com.finpro.auth_service.service.UserService;

@RestController
@RequestMapping("/finpro/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterUserRequest request) {

        User user = userService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

