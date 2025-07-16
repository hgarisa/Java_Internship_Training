package Enums.Order_Status_Tracker;

public class OrderStatusTest
{
    public static void main(String[] args)
    {

        Customer customer = new Customer("George");
        Order order = new Order("ORD123", customer, OrderStatus.OUT_FOR_DELIVERY);
        order.printStatus();
    }
}
