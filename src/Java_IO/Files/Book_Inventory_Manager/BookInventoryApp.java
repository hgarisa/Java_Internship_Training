package Java_IO.Files.Book_Inventory_Manager;
import java.util.List;

/*
Scenario
You work for a bookstore that maintains its inventory in a plain text file named book_inventory.txt.
Each line in the file contains book details in the format:

[Book ID],[Title],[Author],[Price],[Quantity]


*/


/*
 * Main app that reads book inventory and exports out-of-stock books.
 */
public class BookInventoryApp {
    public static void main(String[] args) {
        String inputFile = "book_inventory.txt";
        String outputFile = "out_of_stock_books.txt";

        BookInventoryManager manager = new BookInventoryManager();

        try {
            List<Book> inventory = manager.readInventory(inputFile);
            List<Book> outOfStock = manager.getOutOfStockBooks(inventory);
            manager.writeOutOfStockBooks(outOfStock, outputFile);

            System.out.println("Out-of-stock report generated: " + outputFile);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

