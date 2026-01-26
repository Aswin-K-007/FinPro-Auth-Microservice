package com.finpro.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.finpro.auth_service.dto.LoginRequest;
import com.finpro.auth_service.dto.LoginResponse;
import com.finpro.auth_service.entity.User;
import com.finpro.auth_service.repository.UserRepository;
import com.finpro.auth_service.security.JwtUtility;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtility jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,JwtUtility jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }
        
        return new LoginResponse(jwtUtil.generateToken(request.getUsername()));
    }
}
