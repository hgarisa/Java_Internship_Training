package JavaCollectionsPraticeQuestions.Q3Set;

import java.util.Set;

import java.util.*;

public class CustomerMain
{
    public static  void main(String[] args)
    {
        // 1. Adding elements

    Set<Customer> setone = new HashSet<>();

    Customer c1 = new Customer(129 , "Hrudhay" , "0993871092");
    Customer c2 = new Customer(812 , "Harry" , "081921801933");
    Customer c3 = new Customer(900 , "Hamliton" , "019829130");

        setone.add(c1);
        setone.add(c2);
        setone.add(c3);
        //2. Iterate elements

        for (Customer customer: setone)
        {
            System.out.println(customer);
        }

        System.out.println("Q1,Q2 done-----------------------------------------------------------------------------------------");
        // 3. Search elements

        for (Customer customer: setone)
        {
            if (customer.getName().contains("Hrudhay")) // You can add also .equalsIgnoreCase() method
            {
                System.out.println(customer);
            }
        }

        System.out.println("Q3done-----------------------------------------------------------------------------------------");
        // 4. Modify elements
        for (Customer customer: setone)
        {
            if(customer.getNumber() == 900)
            {
                customer.setName("Lionel");
            }
            System.out.println(customer);
        }

        System.out.println("Q4 Done-----------------------------------------------------------------------------------------");

        for (Customer customer: setone)
        {
            if(customer.getName().equals("Harry"))
            {
                customer.setPhoneNo("8765421092");
            }
            System.out.println(customer);
        }

        System.out.println("Q4Done-----------------------------------------------------------------------------------------");

// 5. Insert and override / Insert back elements
        Customer toReplace = null;
        for (Customer customer : setone) {
            if (customer.getNumber() == 812) {
                toReplace = customer;
                break;
            }
        }

        if (toReplace != null) {
            setone.remove(toReplace);  // remove existing
            Customer newCustomer = new Customer(812, "Harriet", "91122034990"); // override info
            setone.add(newCustomer);   // insert back
        }

        System.out.println("---- After Insert/Override ----");
        for (Customer customer : setone) {
            System.out.println(customer);
        }

        System.out.println("Q5Done-----------------------------------------------------------------------------------------");

        // 6. Check Size of hashSet

        System.out.println(" Hash set size is " + setone.size());
        System.out.println("Q6Done-----------------------------------------------------------------------------------------");

        // 7. Remove elements
        setone.remove(c1);

        for (Customer customer : setone) {
            System.out.println(customer);
        }

        System.out.println("Q7Done-----------------------------------------------------------------------------------------");

        // 8. Create two Hashets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo


        Set<Customer> set2 = new HashSet<>();

        Customer c4 = new Customer(929 , "Qzax" , "1928018");
        Customer c5 = new Customer(812, "Harriet", "91122034990");
        Customer c6 = new Customer(100, "Hardy", "0446581192");


        set2.add(c4);
        set2.add(c5);
        set2.add(c6);

        for (Customer customer : set2)
        {
            System.out.println(customer);
        }

        System.out.println("Q8Done-----------------------------------------------------------------------------------------");

       // 8(i) Loop through these two Hashset's and find the matching customer based on their phoneNo and print that customer.


        Set<Set<Customer>> mainset = new HashSet<>();

        mainset.add(setone);
        mainset.add(set2);

        for ( Customer set1 : setone)
        {
            for ( Customer settwo : set2)
            {
                if (set1.getPhoneNo().equals(settwo.getPhoneNo()))
                {
                    System.out.println(set1);
                }

            }

        }

        System.out.println("Q8(i)Done-----------------------------------------------------------------------------------------");

        System.out.println("Beginning of Task 6 now with Set<Set<Customer>>");
        // Task 6 Set<Set<Customer>>

        // 1. Adding elements
        //2. Iterate elements
       Set<Customer> customerSet1 = new HashSet<>();

       Customer customer = new Customer(99 , "Farah" , "192202183");
        Customer customer2 = new Customer(100 , "Chuntz" , "48732432");
        Customer customer3 = new Customer(101 , "Vini" , "150091986");

        customerSet1.add(customer);
        customerSet1.add(customer2);
        customerSet1.add(customer3);

        Set<Set<Customer>> customersSet = new HashSet<>();
       customersSet.add(customerSet1);

        for (Set<Customer> cust: customersSet )
        {
            System.out.println(cust);
        }

        System.out.println("Q1,Q2 done-----------------------------------------------------------------------------------------");

       // 3. Search elements

        for (Set<Customer> cust: customersSet )
        {

            for (Customer customer1: customerSet1)
            {
                if (customer1.getName().contains("Vini"))
                {

                    System.out.println(customer1);
                }

                //System.out.println(cust);
            }
            //System.out.println(cust);
        }

        System.out.println("Q3 done-----------------------------------------------------------------------------------------");

        //4. Modify elements

        for (Set<Customer> cust: customersSet )
        {

            for (Customer customer1: customerSet1)
            {
                if (customer1.getNumber() == 99)
                {

                    customer1.setName("Mo");
                }

            }
            System.out.println(cust);
        }

        System.out.println("Q4 done-----------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back
        Customer mustReplace = null;

            for (Customer customer1: customerSet1)
            {
                if (customer1.getNumber() == 99)
                {
                    mustReplace = customer1;
                    break;
                }
            }
            if (mustReplace!= null)
            {
                customerSet1.remove(mustReplace);
                Customer newCust = new Customer(12 , "Hrudhay" , "1029102");
                customerSet1.add(newCust);
            }

        for (Set<Customer> c: customersSet)
        {

            System.out.println(c);

        }

        System.out.println("Q5Done-----------------------------------------------------------------------------------------");


       // 6. Check Size of arraylist

        System.out.println(" Size of Customers set is " + customersSet.size());

        System.out.println("Q6Done-----------------------------------------------------------------------------------------");

        // 7. Remove elements

        customerSet1.remove(customer3);

        for (Set<Customer> c: customersSet)
        {

            System.out.println(c);
        }
        System.out.println("Q7Done-----------------------------------------------------------------------------------------");

        // 8. Create two hashsets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo


        Set<Customer> customerSet2 =  new HashSet<>();

        Customer customer5 = new Customer(12 , "Hrudhay" , "1029102");
        Customer customer6 = new Customer(15 , "CR7" , "70228932912");
        Customer customer7 = new Customer(19 , "Virat" , "6710022771");

        customerSet2.add(customer5);
        customerSet2.add(customer6);
        customerSet2.add(customer7);


        customersSet.add(customerSet2);

        for (Set<Customer> c: customersSet)
        {

            System.out.println(c);
        }

        System.out.println("Q8Done-----------------------------------------------------------------------------------------");


        // 8(i) Loop through these two hashsets and find the matching customer based on their phoneNo and print that customer.


        Set<Customer> setOne = customerSet1;
        Set<Customer> setTwo = customerSet2;

        for (Customer customersetnumberone:setOne)
        {

            for (Customer  customersetnumbertwo:setTwo)
            {
                if (customersetnumberone.getPhoneNo().equals(customersetnumbertwo.getPhoneNo()))
                {
                    System.out.println(customersetnumberone);
                }

            }

        }

        System.out.println("Q8(i)Done-----------------------------------------------------------------------------------------");






    }
}
