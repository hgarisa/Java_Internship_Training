package JavaCollectionsPraticeQuestions.Q4MapSecondRedo;

import java.util.*;
public class CustomerMain
{
    public static void main(String[] args)
    {
        System.out.println(" Task is Map<String , Customer>");

//        1. Adding elements
//        2. Iterate elements

        Map<String , Customer> customerMap1 = new HashMap<>();

        customerMap1.put("Z01" , new Customer(1 , "Hrudhay" , "0663021620"));
        customerMap1.put("Z02" , new Customer(2 , "Harish" , "1289378"));
        customerMap1.put("Z03" , new Customer(3 , "Harris" , "89181229292"));
        customerMap1.put("Z04" , new Customer(4 , "Hendo" , "9102381"));

        for (Map.Entry<String , Customer> myMap1 : customerMap1.entrySet())
        {
            System.out.println( myMap1.getKey() +  " => " +  myMap1.getValue());
        }

        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements
        // search by key

        String keyToSearch = "Z03";
        Customer getResult = customerMap1.get(keyToSearch);

        if (getResult != null)
        {
            System.out.println("Search by key" +  keyToSearch + " => " + getResult);
        }
        else
        {

            System.out.println("Customer not found with key " + keyToSearch);
        }

        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        // Search by name

        for (Map.Entry<String , Customer> myMap1 : customerMap1.entrySet())
        {
            Customer customer = myMap1.getValue();

            if (customer.getName().contains("Hrudhay"))
            {
                System.out.println(customer);
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------------------------------");

  //4. Modify elements

        Customer modifyCust = customerMap1.get("Z02");

        if (modifyCust != null)
        {
            modifyCust.setName("Harsha");
            modifyCust.setPhoneNo("81290");

        }
        else
        {
            System.out.println("Customer not found with key ZO2");
        }
        for (Map.Entry<String , Customer> myMap1 : customerMap1.entrySet())
        {

            System.out.println( myMap1.getKey() +  " => " +  myMap1.getValue());
        }
        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------------------------------");

     //5. Insert and override / Insert back elements

        Customer overrideCustomer = customerMap1.get("Z04");

        if (overrideCustomer != null)
        {
            customerMap1.put("Z04" , new Customer(4 , "Jordan Henderson", "19327183"));

        }
        else
        {
            System.out.println("Customer cannot be found with key Z04");
        }
        for (Map.Entry<String , Customer> myMap1 : customerMap1.entrySet())
        {

            System.out.println( myMap1.getKey() +  " => " +  myMap1.getValue());
        }
        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of HashMap
        System.out.println("Size of hashmap is "  + customerMap1.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements
        customerMap1.remove("Z03");

        for (Map.Entry<String , Customer> myMap1 : customerMap1.entrySet())
        {

            System.out.println( myMap1.getKey() +  " => " +  myMap1.getValue());
        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //8. Create two HashMap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Map<String , Customer> customerMap2 = new HashMap<>();
        customerMap2.put("Z01" , new Customer(1 , "Hrudhay" , "0663021620"));
        customerMap2.put("Z02" , new Customer(2 , "Harish" , "1289378"));

        for (Map.Entry<String ,Customer> customerEntry : customerMap2.entrySet())
        {
            System.out.println("Key : " +  customerEntry.getKey() + " => " + customerEntry.getValue());
        }

        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------------------------------");

       // 8(i) Loop through these two HashMap's and find the matching customer based on their phoneNo and print that customer.

        for (Map.Entry<String , Customer> map1 : customerMap1.entrySet())
        {
            Customer c1 = map1.getValue();
            for (Map.Entry<String , Customer> map2 : customerMap2.entrySet())
            {
                Customer c2 = map2.getValue();

                if (c1.getPhoneNo().equals(c2.getPhoneNo()))
                {
                    System.out.println(c1);
                }
            }
        }

        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println(" Task is Map<String , Map<String , Customer>>");

        //1. Adding elements
        //2. Iterate elements

        Map<String , Map<String , Customer>> myMainMap = new HashMap<>();

        myMainMap.put("Map A" , customerMap1);

        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            System.out.println(outerMap.getKey() +  " => " +  outerMap.getValue());
        }
        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------------------------------");
        //3. Search elements

        //Search By Key

        String outerKey = "Map A";
        String InnerKey = "Z01";

        Map<String , Customer> InnerMap = myMainMap.get(outerKey);
        if (InnerMap != null)
        {
            Customer foundCustomer = InnerMap.get(InnerKey);

            if (foundCustomer != null)
            {
                System.out.println("Customer Found : " + foundCustomer);
            }
            else
            {
                System.out.println("No Customer found with Key" + InnerKey);
            }
        }
        else
        {
            System.out.println("No such outer key" +  outerKey);
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");

        //Search By Name
        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            Map<String , Customer> outerMapValue = outerMap.getValue();

            for (Map.Entry<String , Customer> innerMap : outerMapValue.entrySet())
            {

                Customer customer = innerMap.getValue();

                if (customer.getName().contains("Hrudhay"))
                {
                    System.out.println(customer);
                }

            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        Map<String , Customer> innerMap = myMainMap.get("Map A");
        if (innerMap != null)
        {
            Customer modifycustomer = innerMap.get("Z02");
            if (modifycustomer != null)
            {

                modifycustomer.setName("Harper");
                modifycustomer.setPhoneNo("911031209238");
                System.out.println(modifycustomer);
            }
            else
            {
                System.out.println("Customer not found with Key Z02");
            }
        }
        else
        {
            System.out.println("Map A not found with in main map");
        }

        // Updated Map

        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            System.out.println(outerMap.getKey() +  " => " +  outerMap.getValue());
        }

        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back element
        Map<String , Customer> insideMap = myMainMap.get("Map A");
        if (insideMap != null)
        {

            insideMap.put("Z04" , new Customer(4 ,  "Re-Inserted Jordan Henderson" , "912310"));

            System.out.println("Overriding existing customer with Key Z04");
            Customer reInsertedCustomer = insideMap.get("Z01");

            if (reInsertedCustomer != null)
            {
                reInsertedCustomer.setName("Re-Inserted Hrudhay");
                insideMap.put("Z01", reInsertedCustomer);
                System.out.println( "Re-Inserted modified customer with Key Z01 "  + reInsertedCustomer);
            }
        }
        else
        {
            System.out.println("Map A not found with in main map");
        }
        // Updated Map
        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            System.out.println(outerMap.getKey() +  " => " +  outerMap.getValue());
        }

        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of HashMap

        System.out.println("Size of hashmap is " + myMainMap.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements
        Map<String , Customer> insiderMap = myMainMap.get("Map A");
        if (insiderMap != null)
        {

          Customer removedCustomer = insiderMap.remove("Z02");
          if (removedCustomer != null)
          {
              System.out.println("Removed Customer is  " + removedCustomer);
          }
          else
          {
              System.out.println("Customer not found with key Z02");
          }
        }
        else
        {
            System.out.println("Map A not found with in main map");
        }

        // Updated Map
        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            System.out.println(outerMap.getKey() +  " => " +  outerMap.getValue());
        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------------------------------");

     //8. Create two HashMap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        myMainMap.put("Map B" , customerMap2);

        // Updated Map
        for (Map.Entry<String , Map<String , Customer>> outerMap : myMainMap.entrySet())
        {
            System.out.println(outerMap.getKey() +  " => " +  outerMap.getValue());
        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two HashMap's and find the matching customer based on their phoneNo and print that customer.

       Map<String , Customer> mymap1 = myMainMap.get("Map A");
        Map<String , Customer> mymap2 = myMainMap.get("Map B");

        for (Map.Entry<String , Customer> map1: mymap1.entrySet())
        {
            Customer customer1 = map1.getValue();

            for (Map.Entry<String , Customer> map2: mymap2.entrySet())
            {
                Customer customer2 = map2.getValue();

                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }
            }
        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------------------------------");




    }
}
