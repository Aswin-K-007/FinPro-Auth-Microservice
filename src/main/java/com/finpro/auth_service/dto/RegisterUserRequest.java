package com.finpro.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
	
	@NotBlank
    @Size(min = 4, max = 50)
	private String firstName;

    @NotBlank
    @Size(min = 4, max = 50)
    private String lastName;

    private Long mobileNo;
    
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;
}

