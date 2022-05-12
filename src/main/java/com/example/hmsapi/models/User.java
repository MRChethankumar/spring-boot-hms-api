package com.example.hmsapi.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.hmsapi.models.Role;

@Document
public class User {
	@Id
	private String id;
	@Indexed(unique=true)
	private String username;
	@Indexed(unique=true)
	@Email
	private String email;
	private String password;
	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String username, @Email String email, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
