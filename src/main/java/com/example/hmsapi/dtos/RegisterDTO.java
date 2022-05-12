package com.example.hmsapi.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;

@NotNull
public class RegisterDTO {
	@NotNull
	@NotBlank(message = "Username con't be empty.")
	private String username;
	@Email
	@NotBlank(message = "Email con't be empty.")
	private String email;
	@NotNull
	@NotBlank(message = "Password con't be empty.")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
