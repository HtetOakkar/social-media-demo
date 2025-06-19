package com.htetoakkar.socialmedia.authentication.service.impl;

import com.htetoakkar.socialmedia.authentication.security.user.UserPrincipal;
import com.htetoakkar.socialmedia.authentication.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private Long JWT_EXPIRATION;

    @Override
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return doGenerateToken(userPrincipal);
    }

    private String doGenerateToken(UserPrincipal userPrincipal) {
        final String authorities = userPrincipal.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        String userId = userPrincipal.getId().toString();
        String username = userPrincipal.getUsername();
        log.info("Generating JWT token for user: {}, with authorities: {}", username, authorities);
        return io.jsonwebtoken.Jwts.builder()
                .subject(userId)
                .claim("username", username)
                .claim("authorities", authorities)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T extractClaimsFromToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    @Override
    public Claims extractClaims(String token) {
        return extractAllClaims(token);
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
    }

}
