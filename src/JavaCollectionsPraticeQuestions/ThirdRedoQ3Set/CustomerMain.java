package JavaCollectionsPraticeQuestions.ThirdRedoQ3Set;

import java.util.*;
public class CustomerMain
{
    public static void main(String[] args)
    {
        System.out.println("Start with Task of  Set<Customer> ");
        //1. Adding elements
        //2. Iterate elements

        Set<Customer> CustomerHashSet1 = new HashSet<>();
        Customer c = new Customer(1 , "Hrudhay" , "0190221");
        Customer c1 = new Customer(2 , "harry" , "093281399");
        Customer c2 = new Customer(3 , "Lebron" , "3890182910" );
        Customer c3 = new Customer(4 , "Steph" , "9021381901990");
        Customer c4 = new Customer(5, "Duke" , "18947113441");

        CustomerHashSet1.add(c);
        CustomerHashSet1.add(c1);
        CustomerHashSet1.add(c2);
        CustomerHashSet1.add(c3);
        CustomerHashSet1.add(c4);

        for (Customer cu : CustomerHashSet1)
        {
            System.out.println(cu);
        }

        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements
        for (Customer cu : CustomerHashSet1)
        {
            if (cu.getName().contains("Hrudhay"))
            {
                System.out.println(cu);
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        for (Customer cu : CustomerHashSet1)
        {
            if (cu.getNumber() == 2)
            {
               cu.setName("Kai");

            }
            System.out.println(cu);
        }
        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back

        Customer replace = null;

        for (Customer cu : CustomerHashSet1)
        {
           if (cu.getNumber() == 5)
           {
               replace =  cu;
               break;
           }
        }
        if (replace!=null)
        {
            CustomerHashSet1.remove(replace);
            Customer newC = new Customer(110 ,  "Dennis" , "138901");
            CustomerHashSet1.add(newC);
        }

        for (Customer customer: CustomerHashSet1)
        {
            System.out.println(customer);
        }

        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of Hashset
        System.out.println("Size of Hashset is " + CustomerHashSet1.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements

        CustomerHashSet1.remove(c3);

        for (Customer customer: CustomerHashSet1)
        {
            System.out.println(customer);
        }

        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------------");

     // 8. Create two Hashsets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Set<Customer> CustomerHashSet2 = new HashSet<>();
        Customer c5 = new Customer(1 , "Hrudhay" , "0190221");
        Customer c6 = new Customer(77 , "Billy" , "78917302033");
        Customer c7 = new Customer(89 , "Kobe" , "9023832919074" );

        CustomerHashSet2.add(c5);
        CustomerHashSet2.add(c6);
        CustomerHashSet2.add(c7);

        for (Customer cus2: CustomerHashSet2 )
        {
            System.out.println(cus2);
        }

        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two Hashsets's and find the matching customer based on their phoneNo and print that customer.

        for (Customer cus1: CustomerHashSet1)
        {
            for (Customer cus2: CustomerHashSet2 )
            {

                if (cus1.getPhoneNo().equals(cus2.getPhoneNo()))
                {
                    System.out.println(cus1);
                }

            }
        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Start of Task with  Set<Set<Customer>>");

        //1. Adding elements
        //2. Iterate elements

        Set<Set<Customer>> myHashSet = new HashSet<>();
        myHashSet.add(CustomerHashSet1);

        for (Set<Customer> customers: myHashSet)
        {
            System.out.println(customers);
        }

        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements

        for (Set<Customer> customers: myHashSet)
        {
            for (Customer customer: customers)
            {

                if (customer.getName().contains("Dennis"))
                {
                    System.out.println(customer);
                }

            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------------");


        //4. Modify elements

        for (Set<Customer> customers: myHashSet)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 110)
                {
                    customer.setName("Jordan");
                }

            }
            System.out.println(customers);

        }
        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back elements

        Customer mustReplace = null;
        for (Set<Customer> customers: myHashSet)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 2)
                {
                    mustReplace = customer;
                    break;
                }
            }
            if (mustReplace!=null) {break;}
        }

        if (mustReplace != null)
        {
            CustomerHashSet1.remove(mustReplace);
            Customer newC = new Customer(89 , "Kobe" , "9023832919074");
            CustomerHashSet1.add(newC);
        }

        for (Set<Customer> customers: myHashSet)
        {
            for (Customer customer: customers)
            {
                System.out.println(customer);
            }

        }

        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of Hashset
        System.out.println("Size of hashset is " + myHashSet.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------------");

       // 7. Remove elements

        Customer MustRemove = null;

        for (Set<Customer> customers: myHashSet)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 3)
                {
                    MustRemove = customer;
                    break;
                }
            }
            if (MustRemove!=null) {break;}
        }

        if (MustRemove!=null)
        {
            for (Set<Customer> customers: myHashSet)
            {
                customers.remove(MustRemove);
                System.out.println(customers);
            }
        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------------");

        //8. Create two Hashsets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        myHashSet.add(CustomerHashSet2);

        for (Set<Customer> customers: myHashSet)
        {
            System.out.println(customers);
        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------------");


        //8(i) Loop through these two Hashsets's and find the matching customer based on their phoneNo and print that customer.

        Set<Customer> set1 = CustomerHashSet1;
        Set<Customer> set2 = CustomerHashSet2;

        for (Customer customer1 : set1)
        {
            for (Customer customer2 : set2)
            {
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }
            }
        }

        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------------");





    }


 }


