package com.example.hmsapi.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "products")
public class Product {
	@Id
	private String id;
	@DBRef
	private Category category;
	@Indexed(unique=true)
	private String name;
	private String price;
	private Set<String> images = new HashSet<>();
	@DBRef
	private User createdby;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updatedDate;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String id, Category category, String name, String price, Set<String> images, User createdby,
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.images = images;
		this.createdby = createdby;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
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

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
		
}
