package dk.zealand.model;

public class Order {
    private final int id;
    private final Food food;
    private final int quantity;
    private String status;

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

    public void markReady() {
        this.status = "KLAR";
    }

    public void markCanceled() {
        this.status = "ANNULLERET";
    }

    public boolean isPending() {
        return "MODTAGET".equals(this.status);
    }

    public boolean isCanceled() {
        return "ANNULLERET".equals(this.status);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Ret: %s, Antal: %d, Status: %s", id, food.getName(), quantity, status);
    }
}
