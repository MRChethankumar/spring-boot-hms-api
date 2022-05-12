package com.example.hmsapi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.hmsapi.dtos.RegisterDTO;
import com.example.hmsapi.models.User;

public interface UserService {
	User saveUser(RegisterDTO data);
	List<User> getUsers();
	void addUserRole(String username, String name);
	ResponseEntity<?> signin(String username, String Password);
	ResponseEntity<?> signup(RegisterDTO data);
}
