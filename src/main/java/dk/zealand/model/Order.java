package dk.zealand.model;

public class Order {
    private final int id;
    private final Food food;
    private final int quantity;
    private final String status;

    public Order(int id, Food food, int quantity, String status) {
        this.id = id;
        this.food = food;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
