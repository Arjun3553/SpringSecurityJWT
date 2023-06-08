package com.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private final String secret_key = "41f384c115424719d61b530d7bb857f1058400d91b9208a2724d3e0eadb59f11c4fd8883eff6c77125d81c2c662df126746162f084d1bac4168d447c3ec5eef5";

	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generateTokenFromUsername(String username) {

		HashMap<String, Object> claims = new HashMap<>();
		return createToken(claims, username);

	}

	private String createToken(HashMap<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();

	}

	private Key getKey() {
		byte[] keys = Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(keys);
	}
}
