package com.example.edgeservice.food;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("food-catalog-service")
public interface FoodClient {

    @GetMapping("/foods")
    Resources<FoodDTO> getFoods();
}
