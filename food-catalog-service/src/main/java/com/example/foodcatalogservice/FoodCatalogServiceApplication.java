package com.example.foodcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCatalogServiceApplication.class, args);
	}

}
