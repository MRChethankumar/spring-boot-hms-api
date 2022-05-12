package com.example.hmsapi.services.implementations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hmsapi.repositories.RoleRepository;
import com.example.hmsapi.repositories.UserRepository;
import com.example.hmsapi.services.UserService;
import com.example.hmsapi.utils.JWTUtil;
import com.mongodb.MongoWriteException;
import com.example.hmsapi.dtos.RegisterDTO;
import com.example.hmsapi.models.Role;
import com.example.hmsapi.models.User;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public UserServiceImplementation(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	

	@Override
	public User saveUser(RegisterDTO user) {
		// TODO Auto-generated method stub
//		try {
			User staff = new User();
			staff.setUsername(user.getUsername());
			staff.setEmail(user.getEmail());
			Set<Role> roles=new HashSet<>();
			Role role = roleRepository.findByName("ROLE_STAFF");
			roles.add(role);
			staff.setRoles(roles);
			staff.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(staff);
//		}
//		catch (Exception e){
//			throw e;
//		}
		
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserRole(String username, String name) {
		// TODO Auto-generated method stub
//		User user = userRepository.findByUsername(username);
//		Role role = roleRepository.findByName(name);
////		user.getRoles().add(role);
//		Set<Role> roles=new HashSet<>();
//	    roles.add(role);
//	    user.setRoles(roles);
//	    userRepository.save(user);
	}



	@Override
	public ResponseEntity<?> signin(String username, String Password) {
		// TODO Auto-generated method stub
		Map<String, String> data= new HashMap<>();
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
					Password));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			
			String token = jwtUtil.generateJwtToken((org.springframework.security.core.userdetails.User)authenticate.getPrincipal());
			data.put("token", token);
			return new ResponseEntity<>(data,HttpStatus.OK);
		}
		catch(UsernameNotFoundException e) {
			data.put("message", e.getMessage());
			return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			data.put("message", e.getMessage());
			return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
		}
		
	}



	@Override
	public ResponseEntity<?> signup(RegisterDTO data) {
		// TODO Auto-generated method stub
		Map<String, String> response= new HashMap<>();
		try {
			User user = new User();
			user.setUsername(data.getUsername());
			user.setEmail(data.getEmail());
			user.setPassword(passwordEncoder.encode(data.getPassword()));
			Set<Role> roles=new HashSet<>();
			Role role = roleRepository.findByName("ROLE_USER");
			roles.add(role);
			user.setRoles(roles);
			userRepository.save(user);
			response.put("message", "Signed up successfully.");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			if(e instanceof DuplicateKeyException) {
				response.put("message", "Username is taken already. Please choose defferent one.");
			}
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
}
