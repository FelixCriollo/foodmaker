package com.FoodMakerServices.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class TokenUtils {

	private final static String ACCESS_TOKEN_SECRET = "foodLoSOPFYaeSLoMejorPerdonMeioKE";
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	public static String CreateToken(String nombre, String Email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		
		return Jwts.builder()
				.setSubject(Email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
	}
	
	public static UsernamePasswordAuthenticationToken getAuth(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(email, null);
		
		} catch (Exception e) {
			return null;
		}
	}

}
