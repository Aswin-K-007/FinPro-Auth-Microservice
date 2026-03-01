package com.finpro.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finpro.auth_service.dto.UserDetails;
import com.finpro.auth_service.service.UserProfileService;
import com.finpro.auth_service.service.UserService;

@RestController
@RequestMapping("/user/{userId}")
public class UserController {
	
	private final UserService userService ;
	private final UserProfileService userProfileService;
	
	public UserController(UserService userService, UserProfileService userProfileService) {
		this.userService = userService;
		this.userProfileService = userProfileService;
	}
	
	@GetMapping("/profile")
	public UserDetails viewUserDetails(@PathVariable Long userId) {
		userProfileService.getUserProfile(userId);
		return null;
	}
	
	@PostMapping("/new_profile")
	public ResponseEntity<?> addUserProfile(@RequestBody UserDetails userDetails){
		return userProfileService.createUserProfile(userDetails);
	}

}
