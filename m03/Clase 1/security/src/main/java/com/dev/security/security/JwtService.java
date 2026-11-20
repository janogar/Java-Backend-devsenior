package com.dev.security.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {

    //especificar una llave secreta.
    private final SecretKey secretKey;

    public JwtService(){
        this.secretKey = Keys.hmacShaKeyFor("mi-llave-secreta-segura-para-jwt-123456".getBytes());
    }

    public String gerateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                    new Date(System.currentTimeMillis() + 1000 *60*60)
                )
                .signWith(secretKey)
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        return claims.getSubject();
    }

    public boolean isTokenValid(String token){
        try{

            Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token);

            return  true;
        }
        catch(Exception e){
            return false;
        }
    }
}
