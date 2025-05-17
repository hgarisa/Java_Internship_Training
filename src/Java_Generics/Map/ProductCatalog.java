package Java_Generics.Map;

import java.util.*;
public class ProductCatalog
{
    public static void main(String[] args)
    {


        // Create a generic Map with product ID as key and Product as value
        Map<String, Product> productMap = new HashMap<>();

        // Adding entries to the map
        productMap.put("P100", new Product("P100", "Laptop", 799.99));
        productMap.put("P200", new Product("P200", "Smartphone", 499.99));
        productMap.put("P300", new Product("P300", "Tablet", 299.99));

        // Accessing a product by key
        Product p = productMap.get("P100");
        System.out.println("Selected Product: " + p.getName() + " - $" + p.getPrice());

        // --- Iterating with Iterator over keys ---
        System.out.println("\n--- Product Catalog (using key Iterator) ---");
        Iterator<String> keyIterator = productMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Product product = productMap.get(key);
            System.out.println(key + ": " + product.getName() + " - $" + product.getPrice());
        }

        // --- Iterating with Iterator over values ---
        System.out.println("\n--- Products (using value Iterator) ---");
        Iterator<Product> valueIterator = productMap.values().iterator();
        while (valueIterator.hasNext()) {
            Product product = valueIterator.next();
            System.out.println(product.getName() + " - $" + product.getPrice());
        }

        // --- Iterating with enhanced for-loop over keySet ---
        System.out.println("\n--- Product Map (for-each over keySet) ---");
        for (String productId : productMap.keySet()) {
            Product product = productMap.get(productId);
            System.out.println(productId + ": " + product.getName() + " - $" + product.getPrice());
        }

        // --- Iterating with enhanced for-loop over values ---
        System.out.println("\n--- Product List (for-each over values) ---");
        for (Product product : productMap.values()) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }


    }
}
