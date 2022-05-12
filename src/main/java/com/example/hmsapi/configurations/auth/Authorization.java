package com.example.hmsapi.configurations.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authorization extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/v1/signin")) {
			filterChain.doFilter(request, response);
		}
		else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				try {
					String token = authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256("secrete".getBytes());
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = verifier.verify(token);
					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					
//					System.out.println("Roles ====>>>> <<<<<"+roles);
					
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					
					Arrays.stream(roles).forEach(role->{
						authorities.add(new SimpleGrantedAuthority(role));
					});
					
					System.out.println("authorities ====>>>> "+authorities);
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				}
				catch(Exception exception) {
					System.out.printf("Exception", exception.getClass());
					System.out.println("Exception ==> "+exception.getMessage());
					response.setHeader("error", exception.getMessage());
					response.sendError(HttpStatus.FORBIDDEN.value());
					Map<String, String> errors = new HashMap<>();
					errors.put("message", exception.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), errors);
				}
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
	}

}
