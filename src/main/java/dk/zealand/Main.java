package dk.zealand;

import dk.zealand.model.Food;
import dk.zealand.model.Order;
import dk.zealand.service.FoodService;
import dk.zealand.service.OrderService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final FoodService FOOD_SERVICE = new FoodService();
    private static final OrderService ORDER_SERVICE = new OrderService(FOOD_SERVICE);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "3" -> markOrderReady(scanner);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1, 2 eller 3."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("3. Markér bestilling som klar");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private static void showDishes() {
        System.out.println("Retter:");

        List<Food> foods = FOOD_SERVICE.getFoods();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            System.out.printf("%d. %s - %d kr.%n", i + 1, food.getName(), food.getPrice());
        }
    }

    private static void createOrder(Scanner scanner) {
        System.out.println();
        System.out.println("Vælg ret:");
        showDishes();
        System.out.print("Ret: ");
        String foodChoice = scanner.nextLine().trim();

        System.out.print("Antal: ");
        String quantityInput = scanner.nextLine().trim();

        try {
            Food selectedFood = FOOD_SERVICE.getFoods().stream()
                    .filter(food -> String.valueOf(FOOD_SERVICE.getFoods().indexOf(food) + 1)
                            .equals(foodChoice) || food.getName().equalsIgnoreCase(foodChoice))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Ugyldig ret. Vælg en af de tre retter."));

            Order order = ORDER_SERVICE.createOrder(selectedFood.getName(), quantityInput);
            System.out.println();
            System.out.println("Bestilling oprettet:");
            System.out.printf("ID: %d%n", order.getId());
            System.out.printf("Ret: %s%n", order.getFood().getName());
            System.out.printf("Antal: %d%n", order.getQuantity());
            System.out.printf("Status: %s%n", order.getStatus());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markOrderReady(Scanner scanner) {
        System.out.println();
        System.out.print("Id på bestilling der skal markeres som klar: ");
        String idInput = scanner.nextLine().trim();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt id. Indtast et tal.");
            return;
        }

        try {
            Order order = ORDER_SERVICE.markOrderReady(id);
            System.out.println("Bestilling opdateret:");
            System.out.println(order.toString());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
