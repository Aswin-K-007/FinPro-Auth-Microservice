package com.finpro.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finpro.auth_service.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
