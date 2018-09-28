package com.example.shoprecommendertest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoprecommendertest.service.PurchaseService;
import com.example.shoprecommendertest.service.RecommenderService;


@RestController
public class RecommenderController {
	
	@Autowired
	private RecommenderService service;
	@Autowired
	private PurchaseService Pservice;
	
	@GetMapping("/api/recommendations/{productId}")
	public @ResponseBody String listRecommended(@PathVariable String productId) {
		String result = Pservice.getBuyers(productId);
		String[] temp = result.split("\\[")[1].split("\\]")[0].replace('"',' ').replaceAll(" ", "").split(",");
		result = "";
		for(int i=0; i<temp.length; i++) {
			result = result + "userId:" + temp[i] + "purchase:" + Pservice.getPurchases(temp[i]).split("\\[")[1].split("\\]")[0];
		}
		return result;
	}
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "Hello World!";
	}
}
