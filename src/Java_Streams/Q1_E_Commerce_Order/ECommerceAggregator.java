package Java_Streams.Q1_E_Commerce_Order;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ECommerceAggregator
{

    // This method does full aggregation using Streams
    public static Map<String, CustomerOrderSummary> aggregateOrders(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId, // Group orders by customerId
                        Collectors.collectingAndThen(
                                Collectors.toList(), // Group as list of orders for each customer
                                customerOrders -> {
                                    // Perform aggregation for each customer's order list

                                    long count = customerOrders.size(); // Total orders
                                    double total = customerOrders.stream()
                                            .mapToDouble(Order::getOrderAmount)
                                            .sum(); // Total amount
                                    LocalDate latest = customerOrders.stream()
                                            .map(Order::getOrderDate)
                                            .max(LocalDate::compareTo)
                                            .orElse(null); // Latest order date

                                    // Create summary object
                                    return new CustomerOrderSummary(count, total, latest);
                                }
                        )
                ));
       }

    public static void main(String[] args) {
        // Sample input data
        List<Order> orders = List.of(
                new Order("O1", "C1", "Laptop", 1200, LocalDate.of(2024, 10, 1)),
                new Order("O2", "C1", "Mouse", 50, LocalDate.of(2024, 12, 5)),
                new Order("O3", "C2", "Phone", 800, LocalDate.of(2024, 9, 20))
        );

        // Call aggregation logic
        Map<String, CustomerOrderSummary> result = aggregateOrders(orders);

        // Print output
        result.forEach((customerId, summary) -> System.out.println(customerId + " -> " + summary));
    }




    }
