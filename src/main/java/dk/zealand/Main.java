package dk.zealand;

import dk.zealand.model.Food;
import dk.zealand.service.FoodService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final FoodService FOOD_SERVICE = new FoodService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> System.out.println(
                        "Oprettelse af bestillinger er endnu ikke implementeret."
                );
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
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
}
