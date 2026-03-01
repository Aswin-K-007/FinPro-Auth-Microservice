package com.finpro.auth_service.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Link to User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Additional Details
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String pincode;
    private LocalDate dateOfBirth;
    private String profileImageUrl;
}