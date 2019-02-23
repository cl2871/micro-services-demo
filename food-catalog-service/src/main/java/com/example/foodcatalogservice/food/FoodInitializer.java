package com.example.foodcatalogservice.food;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class FoodInitializer implements CommandLineRunner {

    private final FoodRepository foodRepository;

    FoodInitializer(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Pad Thai", "Ramen", "Curry", "Soup Dumplings",
                "Boba", "Pho", "Bulgogi")
                .forEach(food -> foodRepository.save(new Food(food)));

        foodRepository.findAll().forEach(System.out::println);
    }
}