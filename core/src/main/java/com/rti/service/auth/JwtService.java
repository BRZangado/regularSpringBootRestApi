package com.rti.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "fZSMTm7tl3JQoIcWRj8KDv6nA7o6Ilnzh6smRzIIC8E=";
    private static final int JWT_EXPIRATION_HOURS = 3;
    private static final String REFRESH = "refresh";
    private static final String TYPE = "type";
    private static final String ACCESS = "access";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(final String userId, final boolean isRefreshToken) {
        final LocalDateTime expiration = LocalDateTime.now().plusHours(JWT_EXPIRATION_HOURS);
        final Date expirationDate = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());

        Map<String, Object> claims = new HashMap<>();
        claims.put(TYPE, isRefreshToken ? REFRESH : ACCESS);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserId(final String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isRefreshToken(final String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return REFRESH.equals(claims.get(TYPE));
    }

    public LocalDateTime getExpirationDateTime(final String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
