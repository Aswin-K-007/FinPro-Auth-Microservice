package com.finpro.auth_service.service;

import org.springframework.http.ResponseEntity;

import com.finpro.auth_service.dto.UserDetails;
import com.finpro.auth_service.entity.UserProfile;
import com.finpro.auth_service.repository.UserProfileRepository;

public class UserProfileService {
	private final UserProfileRepository userProfileRepo ;
	
	public UserProfileService (UserProfileRepository userProfileRepo) {
		this.userProfileRepo = userProfileRepo;	
	}

	public UserDetails getUserProfile(Long userId) {
		userProfileRepo.findByUser_Id(userId);
		return null;
	}

	public ResponseEntity<?> createUserProfile(UserDetails userDetails) {
		
		UserProfile newProfile = new UserProfile();
		
		return ResponseEntity.ok(userProfileRepo.save(newProfile));
	}

}
