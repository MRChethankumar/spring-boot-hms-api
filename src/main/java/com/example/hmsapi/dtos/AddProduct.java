package com.example.hmsapi.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NotNull
public class AddProduct {
	@NotNull
	@NotBlank(message = "Please provide name.")
	private String name;
	@NotNull
	@NotBlank(message = "Please provide price.")
	private String price;
	@NotNull
	@NotBlank(message = "Please provide category.")
	private String category;
	
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
