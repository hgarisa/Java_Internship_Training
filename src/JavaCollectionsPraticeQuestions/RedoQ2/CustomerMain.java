package JavaCollectionsPraticeQuestions.RedoQ2;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMain
{
    public static void main (String[] args)
    {

//        1. Adding elements
        List<Customer> customerList1 = new ArrayList<>();

        Customer cust1 = new Customer(182391 , "Nruahda" , "1028319");
        Customer cust2 = new Customer(371832 , "Asdfa" , "12938434");
        Customer cust3 = new Customer(6375192 , "Hrudhay" , "663021620");

        customerList1.add(cust1);
        customerList1.add(cust2);
        customerList1.add(cust3);

        //        2. Iterate elements
        for (Customer customer : customerList1)
        {
            System.out.println(customer);
        }
        System.out.println("-------------------------------------------------------------------------------");
        // 3. Search elements

        // Way (1)

//        for (Customer customer : customerList1)
//        {
//            if (customer.getName().toLowerCase().contains("hrudhay"))
//            {
//
//                System.out.println(customer);
//            }
//        }

        // Way (2)

//        for (Customer customer : customerList1)
//        {
//            if (customer.getName().equalsIgnoreCase("HruDhay"))
//            {
//
//                System.out.println(customer);
//            }
//        }

        System.out.println("-------------------------------------------------------------------------------");

        // Modify elements

        Customer ModifyIndexOneCustomer = customerList1.get(1);

        ModifyIndexOneCustomer.setNumber(9180);
        ModifyIndexOneCustomer.setName("Rock");
        ModifyIndexOneCustomer.setPhoneNo("8435267836");

        for (Customer customer : customerList1)
        {
            System.out.println(customer);
        }
        System.out.println("-------------------------------------------------------------------------------");

      //  5. Insert and override / Insert back

        Customer OverrideIndexZeroCustomer  = new Customer(1000 , "John Cena" , "20913821");

        customerList1.set(0 , OverrideIndexZeroCustomer);

        for (Customer customer : customerList1)
        {
            System.out.println(customer);
        }
        System.out.println("-------------------------------------------------------------------------------");


        //6. Check Size of arraylist

        System.out.println(" Size of array list is " + customerList1.size());

        System.out.println("-------------------------------------------------------------------------------");

        //7. Remove elements

        customerList1.remove(2);

        for (Customer customer : customerList1)
        {
            System.out.println(customer);
        }

        System.out.println(" Now Size of array list is " + customerList1.size());

        System.out.println("-------------------------------------------------------------------------------");

     // 8. Create two arraylist's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo


        List<Customer> customerList2 = new ArrayList<>();

        Customer cust4 = new Customer(182391 , "Nruahda" , "1028319");
        Customer cust5 = new Customer(1000 , "John Cena" , "20913821");

        customerList2.add(cust4);
        customerList2.add(cust5);

        System.out.println("Customer list 2");

        //        2. Iterate elements
        for (Customer customer : customerList2)
        {
            System.out.println(customer);
        }
        System.out.println("-------------------------------------------------------------------------------");

     // 8(i) Loop through these two arraylist and find the matching customer based on their phoneNo and print that customer.


        for (Customer customer1: customerList1)
        {

            for (Customer customer2: customerList2)
            {
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }

            }
        }

        System.out.println("End of task 3 ");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Start of task 4");
        //  For Task 4 all the above 8 steps should be done with type List<List<Customer>>.

        // 1. Adding elements
        List<List<Customer>> custlist = new ArrayList<>();
        custlist.add(customerList1);

        //2. Iterate elements
        for (List<Customer> customers : custlist)
        {
            System.out.println(customers);
        }

        System.out.println("-----------------------------------------------------------");
        // 3. Search elements

        // Way (1)
        for (List<Customer> customers : custlist)
        {
            for (Customer customer : customerList1)
            {
            if (customer.getName().toLowerCase().contains("john"))
            {
                System.out.println(customer);
            }

            }
        }

        // Way(2)

//          for (List<Customer> customers : custlist)
//        {
//            for (Customer customer : customerList1)
//            {
//            if (customer.getName().equalsIgnoreCase("john Cena"))
//            {
//                System.out.println(customer);
//            }
//
//            }
//        }

        System.out.println("-------------------------------------------------------------------");

        // 4. Modify elements

    Customer modifyRockelement = customerList1.get(1);
    modifyRockelement.setName("Dwyane");
        modifyRockelement.setNumber(901);

        System.out.println("Updated list");
        for (List<Customer> customers : custlist)
        {
            System.out.println(customers);
        }

        System.out.println("------------------------------------------------------------------------------------");

        // 5. Insert and override / Insert back


        Customer InsertBackZeroElement = new Customer(911 , "Matthew" , "723610121");

        customerList1.set(0 , InsertBackZeroElement);

        System.out.println(" New Updated list");
        for (List<Customer> customers : custlist)
        {
            System.out.println(customers);
        }

        System.out.println("------------------------------------------------------------------------------------");

        // 6. Check Size of arraylist

        System.out.println("Arraylist size for custList is  " + custlist.size());

        System.out.println("------------------------------------------------------------------------------------");

        // Remove Elements

        customerList1.remove(1);

        System.out.println(" New Updated list after remove is ");
        for (List<Customer> customers : custlist)
        {
            System.out.println(customers);
        }

        System.out.println("------------------------------------------------------------------------------------");

// 8. Create two arraylist's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        List<Customer> customerList3 = new ArrayList<>();

        Customer cust7 = new Customer(911 , "Matthew" , "723610121");
        Customer cust8 = new Customer(8219 , "Louis" , "4736377462");
        Customer cust9 = new Customer(665 , "Evra" , "65378620000");

        customerList3.add(cust7);
        customerList3.add(cust8);
        customerList3.add(cust9);

        custlist.add(customerList3);

        System.out.println(" New Updated list after adding  customerList3 is ");
        for (List<Customer> customers : custlist)
        {
            System.out.println(customers);
        }

        System.out.println("----------------------------------------------------------------------------------------------");

        //8(i) Loop through these two arraylist and find the matching customer based on their phoneNo and print that customer


        //customerList1

//        List<Customer> listone = custlist.get(0);
//        List<Customer> listTwo = custlist.get(1);
//
//        for (Customer custlistone : listone)
//        {
//
//            for (Customer custlisttwo : listTwo)
//            {
//
//                if(custlistone.getPhoneNo().equals(custlisttwo.getPhoneNo()))
//                {
//                    System.out.println(custlistone);
//                }
//
//            }
//
//        }
//





    }
}
