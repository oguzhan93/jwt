package com.oguzhan.security.jwt.utils;

import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtUtil {
    private final static long EXPIRATION_TIME = 86_400_000; //24h
    private final String SECRET_KEY = "2cad38ce-b902-4292-acf8-ca66b67a74a1";

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String usernameFromToken = getUsernameFromToken(token);
        return (username.equals(usernameFromToken)) && !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsForToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsForToken(String token) {
        JwtParserBuilder parserBuilder = Jwts.parserBuilder();
        parserBuilder.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()));
        return parserBuilder.build().parseClaimsJws(token).getBody();
    }
}
