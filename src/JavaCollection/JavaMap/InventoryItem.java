package JavaCollection.JavaMap;

import java.util.Objects;

public class InventoryItem
{

    private String name;
    private int quantity;


    public InventoryItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }


    public int getQuantity() {
        return quantity;
    }


    public String toString() {
        return name + " (Stock: " + quantity + ")";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItem)) return false;
        InventoryItem item = (InventoryItem) o;
        return quantity == item.quantity &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }



}
