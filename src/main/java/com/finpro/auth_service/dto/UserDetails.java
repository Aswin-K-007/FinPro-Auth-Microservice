package com.finpro.auth_service.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDetails {
	private Long userId;
	private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String pincode;
    private LocalDate dateOfBirth;
    private String profileImageUrl; 
}
