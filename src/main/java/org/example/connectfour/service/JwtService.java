package org.example.connectfour.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.connectfour.entity.User;


import java.security.Key;
import java.util.Date;

public class JwtService {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(User user) {
        long expirationMillis = 1000 * 60 * 60 * 24;

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getIdent())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key).compact();
    }

}
