package com.example.hmsapi.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hmsapi.dtos.RegisterDTO;
import com.example.hmsapi.services.UserService;

@RestController
@RequestMapping("/api/v1/staff/")
public class StaffController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	@RequestMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> addStaff(@Valid @RequestBody RegisterDTO data) throws IOException{
		Map<String, String> response= new HashMap<>();
//		return userService.signup(data);
		try {
			userService.saveUser(data);
			response.put("message", "Successfully added.");
		}
		catch (Exception e) {
			if(e instanceof DuplicateKeyException) {
				response.put("message", "User exists with username or email.");
				return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
