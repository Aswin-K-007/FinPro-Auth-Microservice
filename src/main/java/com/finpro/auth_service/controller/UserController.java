package com.finpro.auth_service.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finpro.auth_service.dto.UserDetails;
import com.finpro.auth_service.entity.UserProfile;
import com.finpro.auth_service.service.UserProfileService;
import com.finpro.auth_service.service.UserService;

@RestController
@RequestMapping("finpro/user/{userId}")
public class UserController {
	
	private final UserService userService ;
	private final UserProfileService userProfileService;
	
	public UserController(UserService userService, UserProfileService userProfileService) {
		this.userService = userService;
		this.userProfileService = userProfileService;
	}
	
	@GetMapping("/profile")
	public Optional<UserProfile> viewUserDetails(@PathVariable Long userId) {
		return userProfileService.getUserProfile(userId);
	}
	
	@PostMapping("/new_profile")
	public ResponseEntity<?> addUserProfile(@RequestBody UserDetails userDetails){
		return userProfileService.createUserProfile(userDetails);
	}

}
