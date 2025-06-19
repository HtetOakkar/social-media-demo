package com.htetoakkar.socialmedia.authentication.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateToken(Authentication authentication);

    boolean validateToken(String token);

    String extractUsername(String token);

    Claims extractClaims(String token);

}
