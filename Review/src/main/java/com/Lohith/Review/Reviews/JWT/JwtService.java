package com.Lohith.Review.Reviews.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // ✅ Extract the subject (usually email or username)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ✅ Extract userId as a custom claim
    public String extractUserId(String token) {
        return extractAllClaims(token).get("userId", String.class);
    }

    // ✅ Extract role (optional usage)
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ✅ Reusable claim extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ✅ Secure parsing using jjwt 0.12.6
    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();
        return parser.parseSignedClaims(token).getPayload();
    }

    // ✅ Token validity check
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
