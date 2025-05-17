package Java_Generics.Methods;

import java.util.*;
public class Main
{

    public static void main(String[] args) {
        // Create a list to hold Customer objects
        List<Customer> customerList = new ArrayList<>();

        // Create a new Customer
        Customer newCustomer = new Customer("C101", "Alice Smith");

        // Add customer using the generic method and return it
        Customer addedCustomer = GenericUtils.addAndReturn(newCustomer, customerList);

        // Print the added customer
        System.out.println("Added Customer: " + addedCustomer);

        // Print the list contents
        System.out.println("All Customers in List:");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }





    }
}
