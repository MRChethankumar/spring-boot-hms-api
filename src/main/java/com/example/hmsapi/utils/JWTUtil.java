package com.example.hmsapi.utils;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTUtil {
	
	private String secrete ="ADBND63223N%$%^5V#$$gghjhjgghgrsadyghgERdfVAFaatgfh";
	Algorithm algorithm = Algorithm.HMAC256("secrete".getBytes());
	
	public String generateJwtToken(User user) {
		String token = JWT.create().withSubject(user.getUsername())
		.withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
		.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
		.sign(this.algorithm);
		
		return token;
	}
	
}
