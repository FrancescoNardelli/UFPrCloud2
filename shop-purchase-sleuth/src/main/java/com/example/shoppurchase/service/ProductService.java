package com.example.shoppurchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoppurchase.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "defaultProduct")
	public Product getProduct(String productId) {
		return restTemplate.getForObject("http://shop-catalog/api/products/{productId}", Product.class, productId);
	}
	
	@HystrixCommand(fallbackMethod = "defaultBook")
	public Product bookAvailability(String productId, int quantity) {
		return restTemplate.exchange("http://shop-catalog/api/products/{productId}/availability/{quantity}", HttpMethod.PUT, null, Product.class, productId, -quantity).getBody();
	}
	
	public Product defaultProduct(String productId) {
		Product product = new Product();
		product.setId(productId);
		product.setCategory("category");
		product.setAvailability(1000);
		product.setTitle("title");
		product.setPrice(1000.0);
		product.setDescription("description");
		return product;
	}
	
	public Product defaultBook(String productId, int quantity) {
		return defaultProduct(productId);
	}
}
