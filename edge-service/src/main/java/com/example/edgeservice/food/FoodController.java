package com.example.edgeservice.food;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class FoodController {

    @Autowired
    private FoodClient foodClient;

//    @HystrixCommand(fallbackMethod = "fallback")
//    @GetMapping()
//    public Collection<FoodDTO> food() {
//        return foodClient.getFoods()
//                .getContent()
//                .stream()
//                .collect(Collectors.toList());
//    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/noodles")
    public Collection<FoodDTO> noodles() {
        return foodClient.getFoods()
                .getContent()
                .stream()
                .filter(this::isNoodles)
                .collect(Collectors.toList());
    }

    private boolean isNoodles(FoodDTO food) {
        return food.getName().equals("Pad Thai") ||
                food.getName().equals("Ramen") ||
                food.getName().equals("Pho");
    }

    public Collection<FoodDTO> fallback() {
        return new ArrayList<>();
    }
}
