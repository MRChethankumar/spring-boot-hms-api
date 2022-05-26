package com.example.hmsapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hmsapi.models.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
	Product findByName(String name);
	List<Product> findByCategory(String category);
//	List<Product> findByCategory(Category category);
}
