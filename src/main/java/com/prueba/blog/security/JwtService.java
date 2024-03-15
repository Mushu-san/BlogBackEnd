/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Angelo
 */
@Service
public class JwtService {

    @Value("{jwt.secretKey}")
    private String Secret;

    private final String SECRET_KEY = "7307a16c41c20d6c33c52d6f0e036c0509b7eadfeb42e46392a818340fd2710c";
    
    public String getUsername(String token) {
        return this.extractEspecifiedClaim(token, Claims::getSubject);
    }

    public <T> T extractEspecifiedClaim(String token, Function<Claims, T> customer) {
        Claims claims = this.extractAllClaims(token);
        return customer.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignedIn())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username) {
        return this.generateToken(username, new HashMap<>());
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(username)
                .signWith(this.getSignedIn())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .compact();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        return this.getUsername(token).equals(userDetails.getUsername()) && !this.isTokenExpired(token);
    }
    
    public boolean isTokenExpired(String token){
        return this.extractEspecifiedClaim(token, Claims::getExpiration).before(new Date());
    }

    public Key getSignedIn() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
