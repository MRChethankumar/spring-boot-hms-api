package com.example.hmsapi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hmsapi.models.User;
import com.example.hmsapi.repositories.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	

	public AuthUserDetailsService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		User user = userRepository.findByUsername(username);
//		if(user==null) {
//			throw new UsernameNotFoundException("No User found.");
//		}
//		else {
//			System.out.println("User found.");
//		}
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		user.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
