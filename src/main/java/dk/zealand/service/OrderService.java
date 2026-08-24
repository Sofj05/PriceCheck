package dk.zealand.service;

import dk.zealand.model.Food;
import dk.zealand.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final int MAX_ORDERS = 10;
    private static final String STATUS_RECEIVED = "MODTAGET";

    private final List<Order> orders = new ArrayList<>();
    private final FoodService foodService;
    private int nextOrderId = 1;

    public OrderService(FoodService foodService) {
        this.foodService = foodService;
    }

    public Order createOrder(String selectedFoodName, String quantityInput) {
        Food selectedFood = foodService.getFoods().stream()
                .filter(food -> food.getName().equalsIgnoreCase(selectedFoodName))
                .findFirst()
                .orElse(null);

        if (selectedFood == null) {
            throw new IllegalArgumentException("Ugyldig ret. Vælg en af de tre retter.");
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ugyldigt antal. Indtast et tal.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Ugyldigt antal. Antallet skal være positivt.");
        }

        if (orders.size() >= MAX_ORDERS) {
            throw new IllegalStateException("Der kan højst gemmes ti bestillinger.");
        }

        Order order = new Order(nextOrderId++, selectedFood, quantity, STATUS_RECEIVED);
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
