package com.example.shoprecommendertest.rest;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoprecommendertest.domain.Product;
import com.example.shoprecommendertest.service.RecommenderService;


@RestController
public class RecommenderController {
	
	@Autowired
	private RecommenderService service;
	
	@GetMapping("/api/recommendations/{productId}/{userId}")
	public @ResponseBody Page<Product> listRecommended(Pageable pageRequest, @PathVariable String productId, @PathVariable String userId) {
		return null;
	}
}
