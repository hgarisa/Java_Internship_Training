package Java_IO.Files.Book_Inventory_Manager;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Manager class to parse inventory and find out-of-stock books.
 */
public class BookInventoryManager {

    public List<Book> readInventory(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Book> books = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                books.add(new Book(id, title, author, price, quantity));
            }
        }

        return books;
    }

    public List<Book> getOutOfStockBooks(List<Book> books) {
        return books.stream()
                .filter(book -> book.getQuantity() == 0)
                .collect(Collectors.toList());
    }

    public void writeOutOfStockBooks(List<Book> outOfStock, String outputFile) throws IOException {
        List<String> lines = outOfStock.stream()
                .map(Book::toFileFormat)
                .collect(Collectors.toList());

        Files.write(Paths.get(outputFile), lines);
    }
}

