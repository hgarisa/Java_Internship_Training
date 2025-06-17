package JavaCollectionsPraticeQuestions.ThirdRedoQ2List;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain
{
    public static void main(String[] args)
    {
        //1. Adding elements
        //2. Iterate elements

        System.out.println("Start Task with List<Customer>");

        List<Customer> CList1 = new ArrayList<>();

        Customer c = new Customer(1 , "Hrudhay" , "0765098500");
        Customer c2 = new Customer(2 , "ahfa" , "1093221809");
        Customer c3 = new Customer(3 , "Kobe" , "178529");
        Customer c4 = new Customer(4 , "Jordan" , "5728954844");
        Customer c5 = new Customer(5 , "afo" , "17832172");
        CList1.add(c);
        CList1.add(c2);
        CList1.add(c3);
        CList1.add(c4);
        CList1.add(c5);

        for (Customer customer: CList1)
        {
            System.out.println(customer);
        }

        System.out.println("Q1,Q2-Done------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements

        for (Customer customer: CList1)
        {
            if (customer.getName().contains("Hru"))
            {
                System.out.println(customer);
            }
        }
        System.out.println("Q3-Done------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        for (Customer customer: CList1)
        {
            if (customer.getNumber() == 2)
            {
                customer.setName("Clint");
            }
            System.out.println(customer);
        }

        System.out.println("Q4-Done------------------------------------------------------------------------------------------------------------------------");

        //5 .Insert and override / Insert back


        //Customer newCust = new Customer(5 , "KD" , "0129302129");

//        CList1.remove(4);
//        CList1.add(4 , newCust);
//
       // CList1.add(4 , new Customer(5  , "New KD" , ) );

        Customer modifyCustomer = CList1.get(4);

        if (modifyCustomer != null)
        {
            modifyCustomer.setName("Giannis");
            modifyCustomer.setPhoneNo("91879417");
        }
        CList1.set(4 , modifyCustomer);

        for (Customer customer: CList1)
        {
            System.out.println(customer);
        }
        System.out.println("Q5-Done------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of arraylist
        System.out.println("Size of arraylist is " + CList1.size());
        System.out.println("Q6-Done------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements
        CList1.remove(3);
        for (Customer customer: CList1)
        {
            System.out.println(customer);
        }
        System.out.println("Q7-Done------------------------------------------------------------------------------------------------------------------------");

    //8. Create two arraylist's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        List<Customer> CList2 = new ArrayList<>();

        Customer c6 = new Customer(1 , "Hrudhay" , "0765098500");
        Customer c7 = new Customer(2 , "Mufasa" , "1479041790");
        Customer c8 = new Customer(3 , "Giannis" , "12801890");

        CList2.add(c6);
        CList2.add(c7);
        CList2.add(c8);

        for (Customer customer: CList2)
        {
            System.out.println(customer);
        }
        System.out.println("Q8-Done------------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two arraylist and find the matching customer based on their phoneNo and print that customer.

        for (Customer customer1: CList1)
        {
            for (Customer customer2: CList2)
            {
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }
            }

        }

        System.out.println("Q8(i)-Done------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Start Task with List<List<Customer>>");
        //1. Adding elements
        //2. Iterate elements

        List<List<Customer>> mymainList = new ArrayList<>();
        mymainList.add(CList1);

        for (List<Customer> customers : mymainList)
        {
            System.out.println(customers);

        }
        System.out.println("Q1,Q2-Done------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements

        for (List<Customer> customers : mymainList)
        {
            for ( Customer customer: customers)
            {
                if (customer.getName().contains("Kobe"))
                {
                    System.out.println(customer);
                }

            }
        }
        System.out.println("Q3-Done------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        for (List<Customer> customers : mymainList)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 2)
                {
                    customer.setName("Speed");
                }

            }
            System.out.println(customers);
        }

        System.out.println("Q4-Done------------------------------------------------------------------------------------------------------------------------");

        //5 .Insert and override / Insert back

       Customer mustReplace = null;

        for (List<Customer> customers : mymainList)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 5)
                {
                    mustReplace = customer;
                    break;
                }

            }
            if (mustReplace!=null){ break;}
        }

        if (mustReplace!= null)
        {
            CList1.remove(mustReplace);
            Customer NewC = new Customer(654 , "Gavi" , "9102381" );
            CList1.add(NewC);
        }

        for (List<Customer> customers: mymainList)
        {
            System.out.println(customers);
        }

        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

     //6. Check Size of arraylist
        System.out.println("Size of arraylist is " + mymainList.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //7. Remove elements
        Customer mustRemove = null;
        for (List<Customer> customers : mymainList)
        {
            for (Customer customer: customers)
            {
                if (customer.getNumber() == 2)
                {
                    mustRemove = customer;
                    break;
                }
            }
            if (mustRemove!=null){ break;}
        }

        if (mustRemove!=null)
        {
            for (List<Customer> customers: mymainList)
            {
                customers.remove(mustRemove);
                System.out.println(customers);
            }
        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

       // 8. Create two arraylist's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        mymainList.add(CList2);

        for (List<Customer> customers : mymainList)
        {
            System.out.println(customers);
        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

     //8(i) Loop through these two arraylist and find the matching customer based on their phoneNo and print that customer.

      List<Customer> list1 = mymainList.get(0);
        List<Customer> list2 = mymainList.get(1);


        for (Customer customer1 : list1)
        {

            for (Customer customer2 : list2)
            {
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }

            }

        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");





    }
}
