package com.example.hmsapi.services;

import java.util.List;
import java.util.Optional;

import com.example.hmsapi.dtos.AddProduct;
import com.example.hmsapi.models.Category;
import com.example.hmsapi.models.Product;

public interface ProductService {
	Product saveProduct(AddProduct product);
	Product getProduct(String id);
	List<Product> getProducst();
	Product updateProduct(String id, AddProduct product);
	Boolean deleteProduct(String id);
	List<Product> getByCategory(String category);
//	List<Product> getByCategory(Category category);
}
