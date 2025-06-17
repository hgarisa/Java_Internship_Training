package JavaCollectionsPraticeQuestions.Q2;

import java.util.*;

public class CustomerMain
{

    public static void main (String[] args)
    {



   List<Customer> customerList1 = new ArrayList<>();

   Customer cust1 = new Customer(322120 , "John" , "0992212");
   Customer cust2 = new Customer(312120 , "Jason" , "0242212");
   Customer cust3 = new Customer(321120 , "John" , "912819281");

         customerList1.add(cust1);
        customerList1.add(cust2);
        customerList1.add(cust3);

        for (Customer customer: customerList1)
        {
            System.out.println(customer);
        }

        // Modify elements
        Customer modifyCust3 = customerList1.get(1);

        modifyCust3.setNumber(6371592);
        modifyCust3.setName("Hrudhay");
        modifyCust3.setPhoneNo("0663021620");

        System.out.println("--------------------------------------------------");
        System.out.println("Updated list");

        for (Customer customer: customerList1)
        {
            System.out.println(customer);
        }

        // 5. Insert and override / Insert back

        Customer overrideCust1 = new Customer(132910 , "Rohini Raju" , "03910291");

       customerList1.set(0 , overrideCust1 );

        System.out.println("--------------------------------------------------");
        System.out.println("Updated list");

        for (Customer customer: customerList1)
        {
            System.out.println(customer);
        }

        // Check Size of arraylist
        System.out.println(" Size of the array list is " + customerList1.size());

        //Remove elements

//        customerList1.remove(2);
//
//        System.out.println("--------------------------------------------------");
//        System.out.println("Updated list");
//
//        for (Customer customer: customerList1)
//        {
//            System.out.println(customer);
//        }


        //  Search elements

        //(1) way
//        System.out.println("-----------------------------------------------------------------");
//        for (Customer customer: customerList1)
//        {
//            if (customer.getName().contains("rohini"))
//            {
//
//                System.out.println(customer);
//            }
//        }


        System.out.println("-----------------------------------------------------------------");

//        //(2) way
//        for (Customer customer: customerList1)
//        {
//            if (customer.getName().equalsIgnoreCase("RoHini raju"))
//            {
//
//                System.out.println(customer);
//            }
//        }


   // 8. Create two arraylist's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Customer list 2 ");

        List<Customer> customerList2 = new ArrayList<>();

        Customer cust5 = new Customer(9120 , "Sri krishna" , "02910120");
        Customer cust6 = new Customer(132910 , "Rohini Raju" , "03910291");
        Customer cust7 = new Customer(910210 , "Nathan dawe" , "09120193313");


        customerList2.add(cust5);
        customerList2.add(cust6);
        customerList2.add(cust7);

        for (Customer customer: customerList2)
        {
            System.out.println(customer);
        }

        System.out.println("-----------------------------------------------------------");


//        List<List<Customer>> custlist = new ArrayList<>();
//
//        custlist.add(customerList1);
//        custlist.add(customerList2);
//
//        for (List<Customer> customers : custlist)
//        {
//            System.out.println(customers);
//        }

        System.out.println("--------------------------------------------------------------8(i) below ");

        // 8(i) Loop through these two arraylist and find the matching customer based on his phoneNo and print that customer.


        for (Customer customer1: customerList1)
        {

            for (Customer customer2: customerList2 )
            {

                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }
            }
        }







    }


    }

