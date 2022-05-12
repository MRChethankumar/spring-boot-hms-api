package com.example.hmsapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hmsapi.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

}
