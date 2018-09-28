package com.example.shoprecommendertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PurchaseService {

	@Autowired
	private RestTemplate restTemplate;
	
	public String getBuyers(String productId) {
		return restTemplate.getForObject("http://shop-purchase/api/purchases/users/{productId}", String.class, productId);
	}
	
	public String getPurchases(String userId) {
		return restTemplate.getForObject("http://shop-purchase/api/purchases/{userId}", String.class, userId);
	}
}
