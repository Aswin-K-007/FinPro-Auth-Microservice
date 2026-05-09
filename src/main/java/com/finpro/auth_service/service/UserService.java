package com.finpro.auth_service.service;

import java.util.Random;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finpro.auth_service.dto.RegisterUserRequest;
import com.finpro.auth_service.entity.User;
import com.finpro.auth_service.entity.UserProfile;
import com.finpro.auth_service.exception.EmailAlreadyExixtsException;
import com.finpro.auth_service.exception.UserAlreadyExistsException;
import com.finpro.auth_service.repository.UserProfileRepository;
import com.finpro.auth_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserProfileRepository userProfileRepository) {
    	
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userProfileRepository = userProfileRepository;
    }
    
    @Transactional
    public String register(RegisterUserRequest request) {
    	
    	String username = request.getFirstName().toLowerCase() + 
        		request.getLastName().toLowerCase() + 
                new Random().nextInt(1000);

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExixtsException("Email already exists");
        }

        User user = new User();
        UserProfile userProfile = new UserProfile();
        
        user.setUsername(username);
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        User newUser = userRepository.save(user);
        
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setMobileNo(request.getMobileNo());
        userProfile.setUser(newUser);
        userProfileRepository.save(userProfile);
        
        return username;
    }
}

