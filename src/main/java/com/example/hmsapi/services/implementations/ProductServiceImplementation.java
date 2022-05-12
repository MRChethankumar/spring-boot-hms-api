package com.example.hmsapi.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.hmsapi.dtos.AddProduct;
import com.example.hmsapi.exceptions.NotFoundException;
import com.example.hmsapi.models.Product;
import com.example.hmsapi.models.User;
import com.example.hmsapi.repositories.ProductRepository;
import com.example.hmsapi.repositories.UserRepository;
import com.example.hmsapi.services.ProductService;
import com.example.hmsapi.utils.IAuthenticationFacade;

import java.text.SimpleDateFormat;
import java.util.Date; 

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@Override
	public Product saveProduct(AddProduct product) {
		// TODO Auto-generated method stub
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
		Authentication authentication = authenticationFacade.getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName + " Principal User +++>>>>");
		User user = userRepository.findByUsername(currentPrincipalName).orElseThrow(()->new NotFoundException("Not authenticated."));
		Product newProduct = new Product();
		newProduct.setName(product.getName());
		newProduct.setPrice(product.getPrice());  
		newProduct.setCreatedby(user);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		Date now = new Date();
		newProduct.setCreatedDate(now);
		newProduct.setUpdatedDate(now);
		return productRepository.save(newProduct);
	}

	@Override
	public Product getProduct(String id){
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product Not found."));
		return p;
	}

	@Override
	public Product updateProduct(String id, AddProduct product) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product Not found."));
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		Date now = new Date();
		p.setUpdatedDate(now);
		return productRepository.save(p);
	}

	@Override
	public Boolean deleteProduct(String id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Product> getProducst() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

}
