package JavaCollectionsPraticeQuestions.RedoQ3Set;

import java.util.*;

public class CustomerMain
{
    public static void main (String[] args)
    {

        // 1. Adding elements
        //2. Iterate elements
       Set<Customer> CustomerSet1 = new HashSet<>();

        Customer c1 = new Customer(12 , "Hrudhay" , "0663021620");
        Customer c2 = new Customer(13 , "Sheldon" , "006426321771");
        Customer c3 = new Customer(14 , "Benny" , "999210228917");
        Customer c4 = new Customer(15 , "Cole" , "0564273378792");
        Customer c5 = new Customer(16 , "Ashley" , "56432781821");

        CustomerSet1.add(c1);
        CustomerSet1.add(c2);
        CustomerSet1.add(c3);
        CustomerSet1.add(c4);
        CustomerSet1.add(c5);

        for (Customer customer: CustomerSet1)
        {
            System.out.println(customer);
        }

        System.out.println("Q1,Q2-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 3. Search elements

        for (Customer customer: CustomerSet1)
        {
            if (customer.getName().contains("Hrudhay")) //.equalsIgnoreCase() and .equals() is also a solution
            {
                System.out.println(customer);
            }

        }

        System.out.println("Q3-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 4. Modify elements
        for (Customer customer: CustomerSet1)
        {
            if (customer.getNumber() == 14)
            {
                customer.setName("Jackson");
            }

            System.out.println(customer);
        }

        System.out.println("Q4-Done---------------------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back

        Customer custReplace = null;

        for (Customer c: CustomerSet1)
        {
            if (c.getNumber() == 16)
            {
              custReplace = c;
              break;
            }

        }

        if(custReplace!= null)
        {
            CustomerSet1.remove(custReplace);
            Customer newCust = new Customer(99 , "Willy" , "98238092");
            CustomerSet1.add(newCust);
        }

        for ( Customer cu : CustomerSet1)
        {
            System.out.println(cu);
        }

        System.out.println("Q5-Done---------------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of Hashset

        System.out.println("Hashset size is " + CustomerSet1.size());
        System.out.println("Q6-Done---------------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements
        CustomerSet1.remove(c2);
        for ( Customer cu : CustomerSet1)
        {
            System.out.println(cu);
        }
        System.out.println("Q7-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 8. Create two Hashset's of object customer( number , name , phoneNo ) where in each set one customer has the same phoneNo


        Set<Customer> CustomerSet2 = new HashSet<>();

        Customer c7 = new Customer(31 , "Noni" , "27837381937");
        Customer c8 = new Customer(32 , "Eden" , "68213819");
        Customer c9 = new Customer(33 , "Josh" , "2367841");
        Customer c10 = new Customer(12 , "Hrudhay" , "0663021620");

        CustomerSet2.add(c7);
        CustomerSet2.add(c8);
        CustomerSet2.add(c9);
        CustomerSet2.add(c10);


        for (Customer cu: CustomerSet2)
        {
            System.out.println(cu);
        }

        System.out.println("Q8-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 8(i) Loop through these two Hashset's and find the matching customer based on their phoneNo and print that customer.


        for (Customer custone: CustomerSet1)
        {
            for (Customer custtwo:CustomerSet2)
            {
                if (custone.getPhoneNo().equals(custtwo.getPhoneNo()))
                {
                    System.out.println(custone);
                }
            }
        }
        System.out.println("Q8(i)-Done---------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Done with Task  Set<Customer>");
        System.out.println("Next Task is  Set<Set<Customer>> , same questions applied above will be in this task also");

        //1. Adding elements
        //2. Iterate elements

        Set<Customer> cS1 = new HashSet<>();

        Customer cust = new Customer(100 , "ggr" , "10328120");
        Customer cust1 = new Customer(101 , "dds" , "10328120");
        Customer cust2 = new Customer(102 , "vva" , "10328120");
        Customer cust3 = new Customer(103 , "Hardik" , "00919847416260");


        cS1.add(cust);
        cS1.add(cust1);
        cS1.add(cust2);
        cS1.add(cust3);


        Set<Set<Customer>> myset = new HashSet<>();

        myset.add(cS1);

        for (Set<Customer> customers: myset )
        {

            System.out.println(customers);
        }

        System.out.println("Q1,Q2-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 3. Search elements

        for (Set<Customer> customers: myset )
        {
            for (Customer customer: cS1)
            {
                if (customer.getName().contains("Hardik"))
                {
                    System.out.println(customer);
                }
            }
        }

        System.out.println("Q3-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 4. Modify elements

        for (Set<Customer> customers: myset )
        {
            for (Customer customer: cS1)
            {
                if (customer.getNumber() == 101)
                {
                    customer.setName("John");
                }
            }
            System.out.println(customers);
        }

        System.out.println("Q4-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 5. Insert and override / Insert back

        Customer mustReplace = null;

        for (Set<Customer> customers: myset )
        {
            for (Customer customer: cS1)
            {
              if (customer.getNumber() == 100)
              {
                  mustReplace = customer;

              }
              if (mustReplace != null)
              {
                  cS1.remove(mustReplace);
                  Customer newC = new Customer(130 , "Ederson" , "2023491028");
                  cS1.add(newC);
                  break;
              }
            }

            System.out.println(customers);
        }

        System.out.println("Q5-Done---------------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of hashset is

        System.out.println("Size of Hashset is " + myset.size());

        System.out.println("Q6-Done---------------------------------------------------------------------------------------------------------------------------------");

        // 7. Remove elements

        for (Set<Customer> customers: myset )
        {
            for (Customer customer: cS1)
            {
                if (customer.getNumber() == 102)
                {
                    cS1.remove(customer);
                }
            }
            System.out.println(customers);
        }

        System.out.println("Q7-Done---------------------------------------------------------------------------------------------------------------------------------");

     // 8. Create two Hashets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Set<Customer> hashset2 = new HashSet<>();

        Customer hrudhay = new Customer(312 , "Hrudhay" , "0663021620");
        Customer hardik = new Customer(103 , "Hardik" , "00919847416260");
        Customer jeff = new Customer(982 , "Jeff" , "109281091281");

        hashset2.add(hrudhay);
        hashset2.add(hardik);
        hashset2.add(jeff);

        myset.add(hashset2);

        for (Set<Customer> set : myset)
        {
            System.out.println(set);
        }

        System.out.println("Q8-Done---------------------------------------------------------------------------------------------------------------------------------");

     // 8(i) Loop through these two Hashsets and find the matching customer based on their phoneNo and print that customer.

        Set<Customer> set1 = cS1;
        Set<Customer> set2 = hashset2;

        for (Customer cus1: set1)
        {
            for (Customer cus2: set2)
            {
                if (cus1.getPhoneNo().equals(cus2.getPhoneNo()))
                {

                    System.out.println(cus1);

                }
            }
        }
        System.out.println("Q8(i)-Done---------------------------------------------------------------------------------------------------------------------------------");




    }







}
