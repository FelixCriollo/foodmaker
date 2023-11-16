package com.FoodMakerServices.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearer = request.getHeader("Authorization");
		
		if(bearer != null && bearer.startsWith("bearer ")) {
			String token = bearer.replace("bearer ", "");
			UsernamePasswordAuthenticationToken userPt = TokenUtils.getAuth(token);
			SecurityContextHolder.getContext().setAuthentication(userPt);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
