package Enums.Order_Status_Tracker;

public class Order
{
    private String orderId;
    private Customer customer;
    private OrderStatus status;

    public Order(String orderId, Customer customer, OrderStatus status) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
    }

    public void printStatus() {
        System.out.println("Customer: " + customer.getName() + ", Order #" + orderId + " is currently: " + status);
    }


}
