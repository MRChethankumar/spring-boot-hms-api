package com.example.hmsapi.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hmsapi.dtos.AddProduct;
import com.example.hmsapi.models.Category;
import com.example.hmsapi.repositories.CategoryRepository;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
	public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO data) throws IOException{
		Map<String, String> response= new HashMap<>();
		try {
			Category c = new Category();
			c.setName(data.getName());
			c.setCreatedDate(new Date());
			c.setUpdatedDate(new Date());
			categoryRepository.save(c);
		}
		catch(Exception e) {
			if(e instanceof DuplicateKeyException) {
				response.put("message", "Duplicate category name.");
				return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		}
		response.put("message", "Category added successfully.");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}

class CategoryDTO{
	@NotNull
	@NotBlank(message = "Please provide name.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}