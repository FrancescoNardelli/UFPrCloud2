package com.example.shoprecommendertest.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoprecommendertest.domain.Recommendate;
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
		
		//Getting all products that can become a recommendation
		String temp = Pservice.getBuyers(productId);
		String[] users = temp.split("\\[")[1].split("\\]")[0].replace('"',' ').replaceAll(" ", "").split(",");
		List<String> recommendations = new ArrayList<String>();
		for(int i=0; i<users.length; i++) {
			if(!(users[i].equals(userId))) {
				temp = Pservice.getPurchases(users[i]).split("\\[")[1].split("\\]")[0];
				String[] products = temp.split("\\},\\{");
				for(int j=0; j<products.length; j++) {
					products[j] = products[j].replaceAll("\\{", "").replaceAll("\\}", "");
					String tempProduct = products[j].substring(57,93);
					if(!(tempProduct.equals(productId))/* && !(recommendations.contains(tempProduct))*/) {
						recommendations.add(tempProduct);
					}
				}
			}
		}
		
		//Merging them into 1 array without duplicates and a counter
		List<Recommendate> reccomendations2 = new ArrayList<Recommendate>();
		for(int i=0; i<recommendations.size(); i++) {
			Boolean contain = false;
			for(int j=0; j<reccomendations2.size(); j++) {
				if(reccomendations2.get(j).getProductId().equals(recommendations.get(i))) {
					reccomendations2.get(j).counter++;
					contain = true;
				}
			}
			if(!contain) {
				Recommendate temp2 = new Recommendate();
				temp2.setProductId(recommendations.get(i));
				temp2.counter = 1;
				reccomendations2.add(temp2);
			}
		}
		
		//Sorting products by counter (descending)
		/*
		 * Creare una lista ordinata di counters (alto >> piccolo)
		 * Creare una lista di raccomandazioni ordinata in base alla lista dei counter
		 */
		List<Integer> counters = new ArrayList<Integer>();
		for(int j=0; j<reccomendations2.size(); j++) {
			counters.add(reccomendations2.get(j).counter);
		}
		Collections.sort(counters, Collections.reverseOrder());
		List<Recommendate> reccomendationsFinal = new ArrayList<Recommendate>();
		for(int i=0; i<counters.size(); i++) {
			for(int j=0; j<reccomendations2.size(); j++) {
				if(counters.get(i).equals(reccomendations2.get(j).counter) && !(reccomendationsFinal.contains(reccomendations2.get(j)))) {
					reccomendationsFinal.add(reccomendations2.get(j));
				}
			}
		}
		
		//Print first 5 products (those with higher counters)
		String result = "";
		for(int j=0; j<5; j++) {
			result = result + reccomendationsFinal.get(j).getProductId() + " - " + reccomendationsFinal.get(j).counter + "<br>";
		}
		return result;
	}
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "Hello World!";
	}
}
