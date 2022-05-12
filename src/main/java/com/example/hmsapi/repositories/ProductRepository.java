package com.example.hmsapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hmsapi.models.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
	Product findByName(String name);
}
