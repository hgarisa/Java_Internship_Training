package JavaCollectionsPraticeQuestions.SecondRedoQ3Set;

import java.util.HashSet;
import java.util.Set;

public class CustomerMain
{
    public static void main (String[]args)
    {

        System.out.println("Task 3 should be done with type List<Customer>");

        //1. Adding elements
        //2. Iterate elements

        Set<Customer> custHashSet1 = new HashSet<>();
        Customer c = new Customer(11 , "JDQWD" , "19821");
        Customer c1 = new Customer(12 , "Jarod" , "012831012");
        Customer c2 = new Customer(13 , "Jerome" , "00091654765");
        Customer c3 = new Customer(14 , "Jamie" , "664401291023");

        custHashSet1.add(c);
        custHashSet1.add(c1);
        custHashSet1.add(c2);
        custHashSet1.add(c3);

        for (Customer cus : custHashSet1)
        {
            System.out.println(cus);
        }

        System.out.println("Q1,Q2-Done------------------------------------------------------------------------------------------------------------------------------");

        // 3. Search elements

        for (Customer cus : custHashSet1)
        {
           if (cus.getName().toLowerCase().contains("jarod"))
           {
               System.out.println(cus);
           }
        }

        System.out.println("Q3-Done------------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        for (Customer cus : custHashSet1)
        {
            if (cus.getNumber() == 11)
            {
                cus.setName("Hrudhay");
            }
            System.out.println(cus);
        }
        System.out.println("Q4-Done------------------------------------------------------------------------------------------------------------------------------");

        // 5. Insert and override / Insert back


        Customer custReplace = null;

        for (Customer cus : custHashSet1)
        {
            if (cus.getNumber() == 12)
            {
                custReplace = cus;
                break;
            }
        }

        if (custReplace!= null)
        {
            custHashSet1.remove(custReplace);
            Customer newCustomer = new Customer(33 , "Ruben" , "01921094132");
            custHashSet1.add(newCustomer);
        }
        for (Customer cust : custHashSet1)
        {
            System.out.println(cust);

        }

        System.out.println("Q5-Done------------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of Hashets


        System.out.println("Size of HashSet is " + custHashSet1.size());

        System.out.println("Q6-Done------------------------------------------------------------------------------------------------------------------------------");


        //7. Remove elements

        custHashSet1.remove(c2);

        for (Customer custo: custHashSet1)
        {

            System.out.println(custo);
        }
        System.out.println("Q7-Done------------------------------------------------------------------------------------------------------------------------------");

      //8. Create two Hashsets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Set<Customer> hashTwoSet = new HashSet<>();
        Customer c5 = new Customer(200 , "Sachin", "09102812");
        Customer c6 = new Customer(230 , "Viru", "09102812");
        Customer c7 = new Customer(91 , "Gautam", "09102812");
        Customer c9 = new Customer(14 , "Jamie" , "664401291023");
        Customer c15 = new Customer(130 , "Ederson" , "2023491028");

        hashTwoSet.add(c5);
        hashTwoSet.add(c6);
        hashTwoSet.add(c7);
        hashTwoSet.add(c9);
        hashTwoSet.add(c15);


        for (Customer customer2: hashTwoSet)
        {
            System.out.println(customer2);
        }

        System.out.println("Q8-Done------------------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two arraylist and find the matching customer based on their phoneNo and print that customer.

        for (Customer customer1: custHashSet1)
        {
            for (Customer customer2 : hashTwoSet )
            {
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }

            }
        }

        System.out.println("Q8(i)-Done------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("End of Task 3");
        System.out.println("Beginning of Task 4 with Set<Set<Customer>>");

        //1. Adding elements
        //2. Iterate elements

        Set<Set<Customer>> myHashSet = new HashSet<>();
        myHashSet.add(custHashSet1);

        for (Set<Customer> customers : myHashSet)
        {
            System.out.println(customers);
        }

        System.out.println("Q1,Q2-Done------------------------------------------------------------------------------------------------------------------------------");

        // 3. Search elements

        for (Set<Customer> customers : myHashSet)
        {
            for (Customer customer: customers)
            {
                if (customer.getName().contains("Hrudhay"))
                {
                    System.out.println(customer);
                }
            }
        }
        System.out.println("Q3-Done------------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        for (Set<Customer> customers : myHashSet)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 33)
                {
                    customer.setName("Diego");
                }
                System.out.println(customer);
            }
        }
        System.out.println("Q4-Done------------------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back elements

        Customer getReplace =  null;

        for (Set<Customer> customers : myHashSet)
        {
            for (Customer customer: customers)
            {
               if (customer.getName().equals("Jamie"))
               {
                   getReplace = customer;
                   break;
               }
            }
            if (getReplace != null) { break;}
        }

        if (getReplace!=null)
        {
            custHashSet1.remove(getReplace);
            Customer NewC = new Customer(130 , "Ederson" , "2023491028");
            custHashSet1.add(NewC);
        }

        for (Set<Customer> cus: myHashSet)
        {
            System.out.println(cus);
        }
        System.out.println("Q5-Done------------------------------------------------------------------------------------------------------------------------------");

    //6. Check Size of Hashsets
        System.out.println("Size of hashset is " + myHashSet.size());
        System.out.println("Q6-Done------------------------------------------------------------------------------------------------------------------------------");
       // 7. Remove elements

        Customer toRemove = null;

        for (Set<Customer> mycustomers :  myHashSet)
        {
            for (Customer custo: mycustomers)
            {
            if (custo.getNumber() == 33)
            {
                toRemove = custo;
                break;
             }
            }
            if (toRemove != null){ break;}
        }

       if (toRemove!=null)
       {
           for (Set<Customer> customers : myHashSet)
           {
               customers.remove(toRemove);
               System.out.println(customers);
           }
       }
        System.out.println("Q7-Done------------------------------------------------------------------------------------------------------------------------------");

       // 8. Create two Hashset's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        myHashSet.add(hashTwoSet);

        for (Set<Customer> custome : myHashSet)
        {
            System.out.println(custome);
        }
        System.out.println("Q8-Done------------------------------------------------------------------------------------------------------------------------------");

 // 8(i) Loop through these two hashsets and find the matching customer based on their phoneNo and print that customer.
        Set<Customer> myset1 = custHashSet1;
        Set<Customer> myset2 = hashTwoSet ;
        for (Customer cus1: myset1)
        {
            for (Customer cus2 : myset2)
            {
                if (cus1.getPhoneNo().equals(cus2.getPhoneNo()))
                {
                    System.out.println(cus1);
                }

            }
        }

        System.out.println("Q8(i)-Done------------------------------------------------------------------------------------------------------------------------------");






    }
}
