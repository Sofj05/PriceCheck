package dk.zealand.service;

import dk.zealand.model.Food;

import java.util.List;

public class FoodService {
    private final List<Food> foods = List.of(
            new Food("Festivalburger", 59),
            new Food("Sprøde fritter", 35),
            new Food("Vegansk bowl", 65)
    );

    public List<Food> getFoods() {
        return foods;
    }
}
