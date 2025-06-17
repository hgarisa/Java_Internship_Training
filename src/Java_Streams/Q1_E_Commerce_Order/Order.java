package Java_Streams.Q1_E_Commerce_Order;
import java.time.LocalDate;

// Order class representing one order placed by a customer
public class Order
{
    // Order class representing one order placed by a customer
        private String orderId;
        private String customerId;
        private String productName;
        private double orderAmount;
        private LocalDate orderDate;

        public Order(String orderId, String customerId, String productName, double orderAmount, LocalDate orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.productName = productName;
            this.orderAmount = orderAmount;
            this.orderDate = orderDate;
        }


        public String getCustomerId() { return customerId; }
        public double getOrderAmount() { return orderAmount; }
        public LocalDate getOrderDate() { return orderDate; }

}

