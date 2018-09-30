package com.example.shoprecommendertest.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoprecommendertest.service.PurchaseService;
//import com.example.shoprecommendertest.service.RecommenderService;


@RestController
public class RecommenderController {
	
	//@Autowired
	//private RecommenderService service;
	@Autowired
	private PurchaseService Pservice;
	
	@GetMapping("/api/recommendations/{productId}/{userId}")
	public @ResponseBody String listRecommended(@PathVariable String productId,@PathVariable String userId) {
		String temp = Pservice.getBuyers(productId);
		String[] users = temp.split("\\[")[1].split("\\]")[0].replace('"',' ').replaceAll(" ", "").split(",");
		List<String> recommendations = new ArrayList<String>();
		String result = "";
		for(int i=0; i<users.length; i++) {
			if(!(users[i].equals(userId))) {
				temp = Pservice.getPurchases(users[i]).split("\\[")[1].split("\\]")[0];
				String[] products = temp.split("\\},\\{");
				for(int j=0; j<products.length; j++) {
					products[j] = products[j].replaceAll("\\{", "").replaceAll("\\}", "");
					if(!(products[j].substring(57,93).equals(productId))) {
						recommendations.add(products[j].substring(57,93)+"<br>");
					}
				}
			}
		}
		for(int i=0; i<recommendations.size(); i++) {
			result = result + recommendations.get(i);
		}
		return result;
	}
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "Hello World!";
	}
}
