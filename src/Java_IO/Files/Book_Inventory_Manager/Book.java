package Java_IO.Files.Book_Inventory_Manager;

/*
 * Model class for a Book entry in inventory.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;

    public Book(int id, String title, String author, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toFileFormat() {
        return id + "," + title + "," + author + "," + price + "," + quantity;
    }
}
