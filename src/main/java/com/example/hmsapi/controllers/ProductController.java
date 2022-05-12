package com.example.hmsapi.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hmsapi.dtos.AddProduct;
import com.example.hmsapi.exceptions.NotFoundException;
import com.example.hmsapi.models.Product;
import com.example.hmsapi.services.ProductService;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getProducts(HttpServletRequest request){
		System.out.println(request.getUserPrincipal());
		Map<String, List<Product>> response= new HashMap<>();
//		response.put("message", "Fetched successfully.");
		List<Product> products = productService.getProducst();
		response.put("data", products);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
	public ResponseEntity<?> addProduct(@Valid @RequestBody AddProduct data) throws IOException{
		Map<String, String> response= new HashMap<>();
		try {
			productService.saveProduct(data);
		}
		catch (Exception e) {
			if(e instanceof DuplicateKeyException) {
				response.put("message", "Duplicate product name.");
				return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		}
		response.put("message", "Products added successfully.");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable String id, HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		System.out.println(principal);
		Map<String, Product> response= new HashMap<>();
//		try {
			Product product = productService.getProduct(id);
			response.put("data", product);
//		}
//		catch (NotFoundException e) {
//			Map<String, String> respon= new HashMap<>();
//			return new ResponseEntity<>(respon,HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
	public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody AddProduct data) throws IOException{
		productService.updateProduct(id, data);
		return null;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
	public ResponseEntity<?> deleteProduct(@PathVariable String id){
		Map<String, String> response= new HashMap<>();
		productService.deleteProduct(id);
		response.put("message", "Products deleted successfully.");
		return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
	}
	
}
