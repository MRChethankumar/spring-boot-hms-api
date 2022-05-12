package com.example.hmsapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hmsapi.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Role findByName(String name);
}
