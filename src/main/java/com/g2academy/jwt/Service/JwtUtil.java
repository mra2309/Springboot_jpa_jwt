package com.g2academy.jwt.Service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtUtil {
    String generateToken(UserDetails userDetails);
    String generateRefrashToken(HashMap<String, Object> claims, UserDetails userDetails);

    String extractUsername(String token);
    boolean isTokenValid(String token , UserDetails userDetails);
    boolean isTokenExpired(String token);

}
