package com.example.hmsapi.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hmsapi.dtos.LoginDto;
import com.example.hmsapi.dtos.RegisterDTO;
import com.example.hmsapi.services.UserService;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	@RequestMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) throws IOException{
		return userService.signin(loginDto.getUsername(), loginDto.getPassword());
	}
	
	@PostMapping
	@RequestMapping("/signup")
	public ResponseEntity<?> signUpUser(@Valid @RequestBody RegisterDTO data) throws IOException{
		return userService.signup(data);
	}
	
	@GetMapping
	@RequestMapping("/get/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUsers() throws IOException{
		Map<String, String> data= new HashMap<>();
		data.put("Users", "users");
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
}
