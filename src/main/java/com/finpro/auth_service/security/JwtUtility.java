package com.finpro.auth_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    // Reads the shared secret from application.properties
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    
   

    // 1 hour expiration (in milliseconds)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    /**
     * Generates a SecretKey object from the string secret.
     * Note: jwtSecret must be at least 32 characters long.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT token containing the username as subject 
     * and a custom "role" claim for the Gateway to read.
     */
    public String generateToken(String username,String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)                  // "sub" claim
                .setIssuedAt(now)                     // "iat" claim
                .setExpiration(expiryDate)            // "exp" claim
                .signWith(getSigningKey())            // Signs with HS256 automatically
                .compact();
    }
}