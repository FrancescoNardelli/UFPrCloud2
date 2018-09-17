package com.example.shoppurchase.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.shoppurchase.domain.Product;

@Controller
public class EurekaClientController {

	@Autowired
    private DiscoveryClient discoveryClient;
	
	@GetMapping("/discovery/list")
	public @ResponseBody List<ServiceInstance> getCatalogInstances() {
		return discoveryClient.getInstances("shop-catalog");
	}
	
	@GetMapping("/discovery/any")
	public @ResponseBody URI getCatalogInstance() {
		return discoveryClient.getInstances("shop-catalog").get(0).getUri();
	}

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/discovery/product/{id}")
	public @ResponseBody Product getCatalogProducts(@PathVariable String id) {
		
		return restTemplate.getForObject("http://shop-catalog/api/products/{id}", Product.class, id);
	}

	
}
