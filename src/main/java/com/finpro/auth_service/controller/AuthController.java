package com.finpro.auth_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finpro.auth_service.dto.RegisterUserRequest;
import com.finpro.auth_service.entity.User;
import com.finpro.auth_service.service.UserService;

@RestController
@RequestMapping("/finpro/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterUserRequest request) {

        User user = userService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

