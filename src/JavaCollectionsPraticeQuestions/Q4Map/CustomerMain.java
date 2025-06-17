package JavaCollectionsPraticeQuestions.Q4Map;

import JavaCollection.JavaMap.Student;

import java.util.*;
public class CustomerMain
{
    public static void main (String[] args)
    {

        System.out.println("Task with Map<String , Customer>");

        //1. Adding elements

        Map<String , Customer> customerMap = new HashMap<>();
        customerMap.put("Z01" , new Customer(1 , "Hrudhay" , "09120931"));
        customerMap.put("Z02" , new Customer(2 , "Henderson" , "8912471903"));
        customerMap.put("Z03" , new Customer(3 , "Jade" , "129047109"));
        customerMap.put("Z04" , new Customer(4 , "Eden" , "1892471329"));
        customerMap.put("Z05" , new Customer(5 , "Bonucci" , "567292309"));

        // 2. Iterate elements

        for (Map.Entry<String , Customer> customerEntry : customerMap.entrySet())
        {

            System.out.println("Key : " + customerEntry.getKey() + " Value => " +customerEntry.getValue());
        }

        System.out.println("Q1,Q2-Done---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //3. Search elements

      // Search by Key
        String keyToSearch =  "Z03";
        Customer result = customerMap.get(keyToSearch);
        if (result!= null)
        {
            System.out.println("Search by key : " + keyToSearch + " : " + result);
        }
        else
        {
            System.out.println("Customer not found with key " + keyToSearch);
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");
        // Search by name
        for (Customer cust : customerMap.values())
        {
            if (cust.getName().contains("Hrud"))
            {
                System.out.println("Customer found by name " + cust);
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");


        //4. Modify elements
        // Modify a customer by key
        String modifyKey = "Z04"; //Eden
        Customer cust = customerMap.get(modifyKey);
        if (cust!= null)
        {
            System.out.println("Before modification "  + cust);
            cust.setName("Eden Hazard");
            cust.setPhoneNo("9876543210");
            System.out.println("After modification "  + cust);
        }
        else
        {

            System.out.println("No Customer found with key" + modifyKey);
        }


        for (Map.Entry<String , Customer> customerEntry: customerMap.entrySet())
        {

            System.out.println(" Key " + customerEntry.getKey() + " Value " + customerEntry.getValue() );
        }

        System.out.println("Q4-Done--------------------------------------------------------------------------------");

     //5. Insert and override / Insert back element

        System.out.println("Q5 - Insert & Override ----------------------------------------------------------------");

        // Insert New Customer
        customerMap.put("Z06" , new Customer(88 , "Kompany", "7777777777"));
        System.out.println("Inserted new customer with key Z06");

        // Override existing customer

        customerMap.put("Z02" , new Customer(2 ,  "Henderson Updated", "0000000000"));
        System.out.println("Overriding existing customer with Key Z02");

      // Optional re-insert (already updated by reference)
        Customer modCust = customerMap.get("Z01");

        if (modCust!= null)
        {
            modCust.setName("Captain Hrudhay");
            modCust.setPhoneNo("123147813");
            customerMap.put("Z01" , modCust); // Optional, but demonstrates re-inserting
            System.out.println("Reinserted modified customer with key Z01");
        }

        // Display updated map

        for (Map.Entry<String , Customer> customerEntry : customerMap.entrySet())
        {
            System.out.println("Key : " + customerEntry.getKey() +  " => Value:" + customerEntry.getValue());
        }

        System.out.println("Q5-Done --------------------------------------------------------------------------------");

        //6. Check Size of HashMap
        System.out.println("Size of HashMap is " + customerMap.size());
        System.out.println("Q6-Done --------------------------------------------------------------------------------");

        //7. Remove elements

       customerMap.remove("Z03");

        for (Map.Entry<String , Customer> customerEntry: customerMap.entrySet())
        {
            System.out.println("Key : " + customerEntry.getKey() +  " => Value:" + customerEntry.getValue());
        }

        System.out.println("Q7-Done --------------------------------------------------------------------------------");

        //8. Create two HashMap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        Map<String , Customer> customerMap2 = new HashMap<>();
        customerMap2.put("Z01" , new Customer(1 , "Hrudhay" , "09120931"));
        customerMap2.put("Z02" , new Customer(2 , "Henderson" , "8912471903"));
        customerMap2.put("Z04" , new Customer(4 , "Eden" , "1892471329"));
        customerMap2.put("Z05" , new Customer(5 , "Bonucci" , "567292309"));


        for (Map.Entry<String , Customer> customerEntry2: customerMap2.entrySet())
        {
            System.out.println("Key : " + customerEntry2.getKey() +  " => Value:" + customerEntry2.getValue());
        }

        System.out.println("Q8-Done --------------------------------------------------------------------------------");

        //8(i) Loop through these two HashMap's and find the matching customer based on their phoneNo and print that customer.

        System.out.println("Q8(i) - Matching customers by phone number ------------------------------------------------");


        for (Map.Entry<String , Customer> entry1 : customerMap.entrySet())
        {
            Customer customer1 = entry1.getValue();

            for (Map.Entry<String , Customer> entry2 : customerMap2.entrySet())
            {

                Customer customer2 = entry2.getValue();

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
        mainmap.put("Map A" , customerMap);


        for (Map.Entry<String ,  Map<String , Customer>> mainentry : mainmap.entrySet())
        {

            System.out.println(" Map A : " + mainentry.getValue());
        }

        System.out.println("Q1,Q2-Done---------------------------------------------------------------------------------------------------------------------------------------------------------------");


        //4. Modify elements

        // Get the inner map for Map A

        Map<String , Customer> innerMap = mainmap.get("Map A");
        // Get the customer from the inner map
        if (innerMap != null)
        {
            Customer customerToModify = innerMap.get("Z06");
            if (customerToModify != null)
            {
                System.out.println("Before modification " + customerToModify);

                // Modify fields using setters
                customerToModify.setName("Chris");
                customerToModify.setPhoneNo("9999999999");

                System.out.println(" After modification " + customerToModify);
            }
            else
            {
                System.out.println("Customer with Key Z06 not found in Map A ");
            }

        }
        else
        {
            System.out.println("Map A not found in mainmap");
        }


        for (Map.Entry<String ,  Map<String , Customer>> mainentry : mainmap.entrySet())
        {

            System.out.println(" Map A : " + mainentry.getValue());
        }


        System.out.println("Q4-Done---------------------------------------------------------------------------------------------------------------------------------------------------------------");

        //3. Search elements
        // Search by Key

        String outerKey = "Map A";
        String innerKey = "Z02";
        Map<String , Customer> InnerMap = mainmap.get(outerKey);
        if (InnerMap!= null)
        {
            Customer foundCustomer = InnerMap.get(innerKey);
            if (foundCustomer != null)
            {
                System.out.println("Customer Found " + foundCustomer);
            }
            else
            {
                System.out.println("No customer with key" + innerKey);
            }
        }
        else
        {
            System.out.println("No such outer Key " + outerKey);
        }

        System.out.println("Q3-Done--------------------------------------------------------------------------------");

        // Search by name
        for (Map.Entry<String , Map<String , Customer>> outerEntry : mainmap.entrySet())
        {
          Map<String ,Customer> innerMapGroup = outerEntry.getValue();
            for (Map.Entry<String , Customer> innerEntry: innerMapGroup.entrySet())
            {

                Customer customer = innerEntry.getValue();
                if (customer.getName().contains("Hrudhay"))
                {
                    System.out.println(innerEntry.getKey() + " => " + customer);
                }
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------");


        //5. Insert and override / Insert back elements

        System.out.println("Q5 - Insert & Override ----------------------------------------------------------------");

        Map<String , Customer> innerMapToUpdate = mainmap.get("Map A");

        if (innerMapToUpdate != null)
        {
            // Insert New Customer

            innerMapToUpdate.put("Z10" , new Customer(10, "De Bruyne", "8888888888"));
            System.out.println("Inserted new customer with Key Z10");

            // Override existing customer (Z02 already exists)

            innerMapToUpdate.put("Z02" , new Customer(2, "Henderson Overridden", "0000000001"));
            System.out.println("Overriding existing customer with key Z02");

            // Re-insert modified customer (Z01)
            Customer reinserting = innerMapToUpdate.get("Z01");

            if (reinserting != null)
            {

                reinserting.setName("Reinserted Hrudhay");
                reinserting.setPhoneNo("3109371209");
                innerMapToUpdate.put("Z01" , reinserting);  // not strictly necessary, but shows "put" usage
                System.out.println("Reinserted modified customer with key Z01");
            }

        }
        else
        {
            System.out.println("Map A not found in main map");
        }


        // Print updated map

        for (Map.Entry<String , Map<String , Customer>> outerMap : mainmap.entrySet() )
        {

            System.out.println( outerMap.getKey()  +  " => " + outerMap.getValue());
        }

        System.out.println("Q5---Done-------------------------------------------------------------------------------------------------------------------------------");

    //6. Check Size of Map
        System.out.println("Size of main map is " + mainmap.size());

        System.out.println("Q6---Done-------------------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements

        System.out.println("Removing Customer with Key Z02 from Map A");

        Map<String , Customer> innerCustomerToRemoveForm = mainmap.get("Map A");

   if (innerCustomerToRemoveForm != null)
    {
        Customer removed = innerCustomerToRemoveForm.remove("Z02");

        if (removed != null)
        {

            System.out.println("Removed : " + removed);
        }
        else
        {
            System.out.println("Customer with Key Z02 not found in Map A");
        }
    }
   else
   {
       System.out.println("Map A not found in main map ");
   }

        for (Map.Entry<String , Map<String , Customer>> outerMap : mainmap.entrySet() )
        {

            System.out.println(outerMap.getKey()  +  " => " + outerMap.getValue());
        }

        System.out.println("Q7-Done-------------------------------------------------------------------------------------------------------------------------------");

        //8. Create two Hashmap's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo

        mainmap.put("Map B" , customerMap2);

        for (Map.Entry<String , Map<String , Customer>> outerMap : mainmap.entrySet() )
        {

            System.out.println(outerMap.getKey()  +  " => " + outerMap.getValue());
        }

        System.out.println("Q8-Done-------------------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two Hashset's and find the matching customer based on their phoneNo and print that customer.

        Map<String , Customer> myMap1 = mainmap.get("Map A");
        Map<String , Customer> myMap2 = mainmap.get("Map B");
        for (Map.Entry<String, Customer> map1 : myMap1.entrySet())
        {
            Customer c1 = map1.getValue();
            for (Map.Entry<String, Customer> map2 : myMap2.entrySet())
            {
                Customer c2 = map2.getValue();
                if (c1.getPhoneNo().equals(c2.getPhoneNo()))
                {
                    System.out.println(c1);
                }
            }
        }

        System.out.println("Q8(i)-Done-------------------------------------------------------------------------------------------------------------------------------");




    }
}
