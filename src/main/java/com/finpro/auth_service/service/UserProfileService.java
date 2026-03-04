package com.finpro.auth_service.service;

import org.springframework.http.ResponseEntity;

import com.finpro.auth_service.dto.UserDetails;
import com.finpro.auth_service.entity.User;
import com.finpro.auth_service.entity.UserProfile;
import com.finpro.auth_service.repository.UserProfileRepository;
import com.finpro.auth_service.repository.UserRepository;

public class UserProfileService {
	private final UserProfileRepository userProfileRepo ;
	private final UserRepository userRepo;
	
	public UserProfileService (UserProfileRepository userProfileRepo, UserRepository userRepo) {
		this.userProfileRepo = userProfileRepo;	
		this.userRepo = userRepo;
	}

	public UserDetails getUserProfile(Long userId) {
		userProfileRepo.findByUser_Id(userId);
		return null;
	}

	public ResponseEntity<?> createUserProfile(UserDetails userDetails) {
		
		UserProfile newProfile = new UserProfile();
		User user = userRepo.findById(userDetails.getUserId())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
				
		newProfile.setUser(user);
		newProfile.setAddress(userDetails.getAddress());
		newProfile.setCity(userDetails.getCity());
		newProfile.setCountry(userDetails.getCountry());
		newProfile.setFirstName(userDetails.getFirstName());
		newProfile.setLastName(userDetails.getLastName());
		newProfile.setPincode(userDetails.getPincode());
		newProfile.setDateOfBirth(userDetails.getDateOfBirth());
		newProfile.setProfileImageUrl(userDetails.getProfileImageUrl());
		
		return ResponseEntity.ok(userProfileRepo.save(newProfile));
	}

}
