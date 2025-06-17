package Java_Functional_Interfaces.Q2;
import java.util.*;
import java.util.function.*;


//  Product Discount System
//        Scenario:
//        You are building an e-commerce discount engine where discounts vary based on product category
//        and stock availability.
//
//        Task:
//        Create class Product with fields: productId, name, category, price, stock.
//
//        Use:
//
//        Predicate<Product> to filter products eligible for discount (stock > 10).
//
//        Function<Product, Double> to calculate discount based on category.
//
//        Consumer<Product> to apply the final discounted price back to product.
//

public class ProductDiscountSystem {

    // Product class to hold product information
    static class Product {
        int productId;
        String name;
        String category;
        double price;
        int stock;

        public Product(int productId, String name, String category, double price, int stock) {
            this.productId = productId;
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return name + " | Category: " + category + " | Price: " + price + " | Stock: " + stock;
        }
    }

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product(1, "Laptop", "Electronics", 1000, 15),
                new Product(2, "Shoes", "Fashion", 200, 5),
                new Product(3, "Phone", "Electronics", 700, 20)
        );

        Predicate<Product> stockEligible = p -> p.stock > 10;

        Function<Product, Double> calculateDiscount = p -> {
            if (p.category.equalsIgnoreCase("Electronics")) {
                return p.price * 0.90; // 10% discount
            } else {
                return p.price * 0.95; // 5% discount
            }
        };

        Consumer<Product> applyDiscount = p -> {
            if (stockEligible.test(p)) {
                p.price = calculateDiscount.apply(p);
            }
        };

        products.forEach(applyDiscount);
        products.forEach(System.out::println);
    }

    // The Product class represents each item in the catalog.
// The system first filters eligible products using Predicate.
// Based on category, it calculates discount via Function.
// The Consumer updates the product with the discounted price.

}

