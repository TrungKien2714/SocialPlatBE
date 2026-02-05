package com.SocialPlat.SocialPlat.security.util;

import com.SocialPlat.SocialPlat.security.user.UserDetailimp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;
@Component
public class JWTutil {

    private final String SECRET_KEY = "day-la-chuoi-bi-mat-sieu-cap-vip-pro-123456";
    private final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 giờ


    public String generateToken(UserDetailimp user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
