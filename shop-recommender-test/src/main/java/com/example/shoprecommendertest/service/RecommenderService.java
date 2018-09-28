package com.example.shoprecommendertest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.shoprecommendertest.domain.Product;

@Service
public class RecommenderService {

	public Page<Product> aaa(String userId) {
		return null;
	}
	
	public String splitContent(String content) {
		return content.split("\\[")[1].split("\\]")[0].replace('"',' ').replaceAll(" ", "");
	}
}
