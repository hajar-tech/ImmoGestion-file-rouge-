package com.immoGestion.backend.security;

import com.immoGestion.backend.models.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey = "maCleSuperSecrete123456789012345";
    private final long expirationTime = 86400000;// 1 jour


    public String generateToken(Utilisateur user) {
        String role = user.getClass().getSimpleName().toUpperCase();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role" , role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

}
