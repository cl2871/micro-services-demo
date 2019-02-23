package com.example.foodcatalogservice.food;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Food {

    public Food(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private long id;

    private String name;
}
