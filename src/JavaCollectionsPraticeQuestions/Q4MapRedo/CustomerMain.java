package JavaCollectionsPraticeQuestions.Q4MapRedo;


import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerMain
{
    public static void main (String[]args)
    {
        System.out.println("Task with Map<String , Customer>");
        //1. Adding elements

        Map<String , Customer> customerMap1 = new HashMap<>();

        customerMap1.put("Z01" , new Customer(1 , "Hrudhay" , "066321620"));
        customerMap1.put("Z02" , new Customer(2 , "Harris" , "16294"));
        customerMap1.put("Z03" , new Customer(3 , "Hemsworth" , "123719238"));
        customerMap1.put("Z04" , new Customer(4 , "Hardy" , "8903821"));


        //2. Iterate elements

        for (Map.Entry<String , Customer> map : customerMap1.entrySet())
        {
            System.out.println(map.getKey() + " => " + map.getValue());
        }
        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------");

        //3. Search elements
        //Search by Key
        String keyToSearch  = "Z03";
        Customer result = customerMap1.get(keyToSearch);

        if (result != null)
        {
            System.out.println(" Search by key " + keyToSearch + " => " + result);

        }
        else
        {
            System.out.println("Customer not found with key" + keyToSearch);
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");

        //  Search by name
        for (Map.Entry<String , Customer> map : customerMap1.entrySet())
        {

            Customer cu = map.getValue();
            if (cu.getName().contains("Hrud"))
            {
                System.out.println( map.getKey() + " => " + cu);
            }
        }

        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------");

        //4.Modify Elements
        Customer modifyCustomer = customerMap1.get("Z04");
        if (modifyCustomer!= null)
        {
            System.out.println("Before Modification" + modifyCustomer);
            modifyCustomer.setName("Eden Hazard");
            modifyCustomer.setPhoneNo("9999999999999");
            System.out.println("After Modification " + modifyCustomer);
        }
        else
        {
            System.out.println("No customer found with key Z04");
        }

        for (Map.Entry<String , Customer> customerEntry : customerMap1.entrySet())
        {

            System.out.println( customerEntry.getKey() +  " => " + customerEntry.getValue());
        }

        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back element

      //  Override existing customer

        customerMap1.put("Z03" , new Customer(99  , "Ricky" , "810928"));
        System.out.println("Overriding existing customer with key Z03");

        // Optional re-insert (already updated by reference)

        Customer modifyCust  = customerMap1.get("Z02");

        if (modifyCust!= null)
        {
            modifyCust.setName("Kamala");
            modifyCust.setPhoneNo("190243810");
            customerMap1.put("Z02" , modifyCust); // Optional , but demonstrates re-inserting
            System.out.println("Re-inserted modified customer with key Z02");
        }
        // Display Updated Map

        for (Map.Entry<String , Customer> customerEntry : customerMap1.entrySet())
        {
            System.out.println("Key : " +  customerEntry.getKey() + " => " + customerEntry.getValue());
        }

        System.out.println("Q5-Done --------------------------------------------------------------------------------");

          //6. Check Size of HashMap
        System.out.println(" Size of HashMap is " + customerMap1.size());
        System.out.println("Q6-Done --------------------------------------------------------------------------------");
        //7. Remove elements
        customerMap1.remove("Z04");

        for (Map.Entry<String , Customer> customerEntry : customerMap1.entrySet())
        {
            System.out.println("Key : " +  customerEntry.getKey() + " => " + customerEntry.getValue());
        }
        System.out.println("Q7-Done --------------------------------------------------------------------------------");

        //8. Create two HashMap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Map<String , Customer> customerMap2 = new HashMap<>();
        customerMap2.put("Z01" , new Customer(1 , "Hrudhay" , "066321620"));
        customerMap2.put("Z02" , new Customer(2 , "Harris" , "16294"));
        customerMap2.put("Z03" , new Customer(3 , "Hemsworth" , "123719238"));

        for (Map.Entry<String , Customer> customerEntry : customerMap2.entrySet())
        {
            System.out.println("Key : " +  customerEntry.getKey() + " => " + customerEntry.getValue());
        }
        System.out.println("Q8-Done --------------------------------------------------------------------------------");

        //8(i) Loop through these two HashMap's and find the matching customer based on their phoneNo and print that customer.

        for (Map.Entry<String , Customer> customerEntry1 : customerMap1.entrySet() )
        {

            Customer customer1 = customerEntry1.getValue();

            for (Map.Entry<String , Customer> customerEntry2 : customerMap2.entrySet() )
            {

                Customer customer2 = customerEntry2.getValue();

                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer1);
                }

            }

        }
        System.out.println("Q8(i)-Done --------------------------------------------------------------------------------");

        System.out.println(" Now Task with Map<Map<String , Customer>>");

        //1. Adding elements
        //2. Iterate elements


        Map<String , Map<String , Customer>> mainmap = new HashMap<>();

        mainmap.put("Map A" , customerMap1);

        for ( Map.Entry<String , Map<String , Customer>> mapEntry: mainmap.entrySet())
        {
            System.out.println(" Key : " + mapEntry.getKey() + " => " + mapEntry.getValue());
        }
        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------");

        //3. Search elements
        // Search By Key
        String outerKey = "Map A";
        String innerKey = "Z02";

        Map<String , Customer> InnerMap = mainmap.get(outerKey);
        if (InnerMap != null)
        {
            Customer foundCustomer = InnerMap.get(innerKey);
            if (foundCustomer!= null)
            {
                System.out.println( "Customer Found ,  " + foundCustomer);
            }
            else
            {
                System.out.println("No customer with key" + innerKey);
            }

        }
        else
        {
            System.out.println("No such outer key" + outerKey);
        }

        System.out.println("Q3-Done--------------------------------------------------------------------------------");
        // Search by name

        for (Map.Entry<String ,  Map<String , Customer>> outerEntry : mainmap.entrySet())
        {
            Map<String , Customer> innerMap = outerEntry.getValue();
            for (Map.Entry<String , Customer> innerEntry : innerMap.entrySet())
            {
                Customer customer = innerEntry.getValue();
                if (customer.getName().contains("Hrud"))
                {
                    System.out.println(customer);
                }
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");

        //4. Modify elements
        // Get the inner map from Map A
        Map<String , Customer> innerMap = mainmap.get("Map A");
        if (innerMap != null)
        {
            Customer customerToModify = innerMap.get("Z03");
            if (customerToModify != null)
            {
                System.out.println("Before Modification " + customerToModify);
                // Modify fields using setters
                 customerToModify.setName("Cruise");
                customerToModify.setPhoneNo("109418021");
                System.out.println("After Modification " + customerToModify);

            }
            else
            {
                System.out.println("Customer with key Z03 not found in Map A");
            }
        }
        else
        {

            System.out.println("Map A not found in main map");
        }
        for ( Map.Entry<String , Map<String , Customer>> mapEntry: mainmap.entrySet())
        {
            System.out.println(" Key : " + mapEntry.getKey() + " => " + mapEntry.getValue());
        }
        System.out.println("Q4-Done--------------------------------------------------------------------------------");

        //5. Insert and override / Insert back elements


        Map<String , Customer> innerMapToUpdate = mainmap.get("Map A");

        if (innerMapToUpdate != null)
        {
            // Override existing customer (Z02 already exists)
            innerMapToUpdate.put("Z02" , new Customer(2 , "Harris" , "16294"));
            System.out.println("Overriding existing customer with key Z02");

            // Re-Inserting modified customer (Z01)

            Customer reInsert = innerMapToUpdate.get("Z01");

            if (reInsert != null)
            {
                reInsert.setName("Re-Inserted Hrudhay");

                innerMapToUpdate.put("Z01" , reInsert); //  // not strictly necessary, but shows "put" usage
                System.out.println("Re-Inserted modified customer with Key Z01");
            }
        }
        else
        {
            System.out.println("Map A not found in main map");
        }

        // Print updated map

        for ( Map.Entry<String , Map<String , Customer>> mapEntry: mainmap.entrySet())
        {
            System.out.println(" Key : " + mapEntry.getKey() + " => " + mapEntry.getValue());
        }
        System.out.println("Q5-Done--------------------------------------------------------------------------------");

        //6. Check Size of Map
        System.out.println(" Size of Main map is " + mainmap.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------");

        //7. Remove elements

        Map<String , Customer> innerCustomerToRemove = mainmap.get("Map A");

        if (innerCustomerToRemove != null)
        {

            Customer removed = innerCustomerToRemove.remove("Z03");

            if (removed != null)
            {
                System.out.println("Removed" + removed);
            }
            else
            {
                System.out.println("Customer with key Z03 not found in Map A");
            }
        }
        else
        {
            System.out.println("Map A not found in main map");
        }

        for ( Map.Entry<String , Map<String , Customer>> mapEntry: mainmap.entrySet())
        {
            System.out.println(" Key : " + mapEntry.getKey() + " => " + mapEntry.getValue());
        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------");


        //8. Create two Hashmap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        mainmap.put("Map B" , customerMap2);

        for ( Map.Entry<String , Map<String , Customer>> mapEntry: mainmap.entrySet())
        {
            System.out.println(" Key : " + mapEntry.getKey() + " => " + mapEntry.getValue());
        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------");

        //8(i) Loop through these two Hashset's and find the matching customer based on their phoneNo and print that customer.

        Map<String , Customer> mySet1 = mainmap.get("Map A");
        Map<String , Customer> mySet2 = mainmap.get("Map B");

        for (Map.Entry<String , Customer> set1 : mySet1.entrySet())
        {
            Customer customer1 = set1.getValue();

            for (Map.Entry<String , Customer> set2: mySet2.entrySet())
            {
                Customer customer2 = set2.getValue();

                if (customer1.getPhoneNo().equals(customer2.getPhoneNo()))
                {
                    System.out.println(customer2);
                }

            }

        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------");




    }
}
