package com.example.foodcatalogservice.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FoodRepository extends JpaRepository<Food, Long> {
}
