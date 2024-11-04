package com.poll.utils;

import com.poll.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final SecretKey signingKey = Jwts.SIG.HS256.key().build();
    private static final Long expirationTime = 86400000L;

    private static Map<String, Object> generateClaim(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getUserId());
        claims.put("isAdmin", user.getIsAdmin());
        claims.put("email", user.getEmail());
        return claims;
    }

    public static String generateJwt(User user) {
        return Jwts.builder()
                .claims(generateClaim(user))
                .signWith(signingKey)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public static String logOutJwt(User user) {
        return Jwts.builder()
                .claims(generateClaim(user))
                .signWith(signingKey)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public static User parseJwt(String jwt) {
        Claims claims =  Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(jwt).getPayload();
        User user = new User();
        user.setUserId(Integer.parseInt(claims.get("userId").toString()));
        user.setUsername(claims.get("username", String.class));
        user.setEmail(claims.get("email", String.class));
        user.setIsAdmin(claims.get("isAdmin", Boolean.class));
        return user;
    }
}
