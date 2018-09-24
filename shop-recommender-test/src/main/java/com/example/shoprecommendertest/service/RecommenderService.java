package com.example.shoprecommendertest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.shoprecommendertest.domain.Product;

@Service
public class RecommenderService {

	public Page<Product> getPurchasesFromUserID(String userId) {
		return null;
	}
}
