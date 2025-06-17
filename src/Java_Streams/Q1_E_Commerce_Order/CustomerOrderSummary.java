package Java_Streams.Q1_E_Commerce_Order;

import java.time.LocalDate;

// Class representing final summary for each customer
public class CustomerOrderSummary
{
    private long totalOrders;
    private double totalSpent;
    private LocalDate latestOrderDate;

    public CustomerOrderSummary(long totalOrders, double totalSpent, LocalDate latestOrderDate) {
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
        this.latestOrderDate = latestOrderDate;
    }


    @Override
    public String toString() {
        return "Total Orders: " + totalOrders + ", Total Spent: " + totalSpent + ", Latest Order: " + latestOrderDate;
    }


}
