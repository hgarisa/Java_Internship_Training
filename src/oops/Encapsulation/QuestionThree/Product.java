package oops.Encapsulation.QuestionThree;

class Product {
    private final String productId;  // Final: cannot change after creation
    private String name;
    private int stock;

    public Product(String productId, String name, int stock) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
    }

    // Getter for productId (no setter — read-only)
    public String getProductId() {
        return productId;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isBlank()) {
            this.name = name;
        }
    }

    // Stock logic
    public int getStock() {
        return stock;
    }

    public void restock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
        }
    }

    public void sell(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Insufficient stock for sale.");
        }
    }
}