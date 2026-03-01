package com.finpro.auth_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.finpro.auth_service.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	Optional<UserProfile> findByUser_Id(Long userId);
}


