package JavaCollectionsPraticeQuestions.Q4MapThirdRedo;

import java.util.*;

public class CustomerMain {
    public static void main(String[] args) {

        System.out.println("Task with Map<String , Customer>");

        //1. Adding elements
        //2. Iterate elements

        Map<String, Customer> customerMap1 = new HashMap<>();
        customerMap1.put("Z01", new Customer(1, "Hrudhay", "0663021620"));
        customerMap1.put("Z02", new Customer(2, "Harry", "91027431"));
        customerMap1.put("Z03", new Customer(3, "Herbert", "901247120"));
        customerMap1.put("Z04", new Customer(4, "Hamilton", "323013291"));
        customerMap1.put("Z05", new Customer(5, "Houston", "2793052"));

        for (Map.Entry<String, Customer> customers : customerMap1.entrySet()) {
            System.out.println(customers.getKey() + "=>" + customers.getValue());

        }
        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------");
        //3. Search elements
        //Search By Key
        String keyToSearch = "Z03";

        Customer foundcustomer = customerMap1.get(keyToSearch);
        if (foundcustomer != null) {
            System.out.println(" Found customer : " + foundcustomer + " of key " + keyToSearch);
        } else {
            System.out.println("Customer not found with key " + keyToSearch);
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------");

        //Search by Name
        for (Map.Entry<String, Customer> customerEntry1 : customerMap1.entrySet()) {
            Customer customer = customerEntry1.getValue();
            if (customer.getName().contains("Hrudhay")) {
                System.out.println(customer);
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements

        Customer modifyCustomer = customerMap1.get("Z04");
        if (modifyCustomer != null) {
            modifyCustomer.setName("Brad");
            modifyCustomer.setPhoneNo("66025748");
            System.out.println("Modified Customer is " + modifyCustomer);
        } else {
            System.out.println("Could not modify customer with key Z04");
        }

        for (Map.Entry<String, Customer> customers : customerMap1.entrySet()) {
            System.out.println(customers.getKey() + "=>" + customers.getValue());

        }

        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------");

        //5. Insert and override / Insert back elements

        // Way (1 )
        customerMap1.put("Z03", new Customer(3, "Marsh", "891023812"));

        // Way(2)
        Customer ReInsertCustomer = customerMap1.get("Z02");
        if (ReInsertCustomer != null) {
            ReInsertCustomer.setName("Harry Lewis");
            ReInsertCustomer.setPhoneNo("19028313");
            customerMap1.put("Z02", ReInsertCustomer);
            System.out.println("Re Inserted Customer is " + ReInsertCustomer);
        } else {
            System.out.println("Could not re insert a customer");
        }

        for (Map.Entry<String, Customer> customers : customerMap1.entrySet()) {
            System.out.println(customers.getKey() + "=>" + customers.getValue());

        }
        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of HashMap
        System.out.println("Size of hashmap is " + customerMap1.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements
        Customer removedCustomer = customerMap1.remove("Z05");
        System.out.println("Removed customer " + removedCustomer + " from map ");
        for (Map.Entry<String, Customer> customers : customerMap1.entrySet()) {
            System.out.println(customers.getKey() + "=>" + customers.getValue());

        }
        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------");

        //8. Create two HashMap's of object customer( number , name , phoneNo ) where in each hashmap one customer has the same phoneNo

        Map<String, Customer> customerMap2 = new HashMap<>();
        customerMap2.put("Z01", new Customer(1, "Hrudhay", "0663021620"));
        customerMap2.put("Z02", new Customer(2, "Harry", "91027431"));
        customerMap2.put("Z03", new Customer(3, "Herbert", "901247120"));

        for (Map.Entry<String, Customer> customers : customerMap2.entrySet()) {
            System.out.println(customers.getKey() + "=>" + customers.getValue());

        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------");

        // 8(i) Loop through these two HashMap's and find the matching customer based on their phoneNo and print that customer.

        for (Map.Entry<String, Customer> customerEntry : customerMap1.entrySet()) {

            Customer customer1 = customerEntry.getValue();

            for (Map.Entry<String, Customer> customerEntry2 : customerMap2.entrySet()) {
                Customer customer2 = customerEntry2.getValue();

                if (customer1.getPhoneNo().equals(customer2.getPhoneNo())) {
                    System.out.println(customer1);
                }

            }

        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------");


        System.out.println("Task with Map<String , Map<String , Customer>>");

        //1. Adding elements
        //2. Iterate elements

        Map<String, Map<String, Customer>> mainmap = new HashMap<>();

        mainmap.put("Map A", customerMap1);

        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            System.out.println(outerMap.getKey() + " => " + outerMap.getValue());
        }
        System.out.println("Q1,Q2-Done--------------------------------------------------------------------------------------------------------------------");
        //3. Search elements
        //Search by Key
        String outerKey = "Map A";
        String innerKey = "Z01";

        Map<String, Customer> getInnerMap = mainmap.get(outerKey);
        if (getInnerMap != null) {
            Customer findCustomer = customerMap1.get(innerKey);
            if (findCustomer != null) {
                System.out.println(" Found customer with key " + innerKey + " The Customer is " + findCustomer);
            } else {
                System.out.println("Could not find customer with key" + innerKey);
            }
        } else {
            System.out.println("Could not find map with outer key " + outerKey);
        }

        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------");

        // Search by Name
        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            Map<String, Customer> innerMap = outerMap.getValue();

            for (Map.Entry<String, Customer> innerValue : innerMap.entrySet()) {

                Customer customer = innerValue.getValue();

                if (customer.getName().contains("Hrud")) {
                    System.out.println(customer);
                }
            }
        }
        System.out.println("Q3-Done--------------------------------------------------------------------------------------------------------------------");

        //4. Modify elements
        Map<String, Customer> innerMap = mainmap.get("Map A");

        if (innerMap != null) {
            Customer editCustomer = innerMap.get("Z02");
            if (editCustomer != null) {
                editCustomer.setName("Luke");
                editCustomer.setPhoneNo("91032715674366666");
                System.out.println(" Modified customer is " + editCustomer);
            } else {
                System.out.println("Customer could not be found");
            }
        } else {
            System.out.println("Inner map could not be found");
        }
        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            System.out.println(outerMap.getKey() + " => " + outerMap.getValue());
        }
        System.out.println("Q4-Done--------------------------------------------------------------------------------------------------------------------");


        //5. Insert and override / Insert back
        Map<String, Customer> insideMap = mainmap.get("Map A");

        if (insideMap != null) {
            // Way 1
            insideMap.put("Z03", new Customer(3, "Fabrizo", "011283192800"));

            // Way 2
            Customer reModifiedCustomer = insideMap.get("Z04");
            if (reModifiedCustomer != null) {
                reModifiedCustomer.setName("Mo-Farah");
                reModifiedCustomer.setPhoneNo("00618237219");
                insideMap.put("Z04", reModifiedCustomer);
                System.out.println("The new  modified customer is " + reModifiedCustomer);
            } else {
                System.out.println("Could not reinsert customer");
            }

        } else {
            System.out.println("Could not find innerMap ");
        }

        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            System.out.println(outerMap.getKey() + " => " + outerMap.getValue());
        }

        System.out.println("Q5-Done--------------------------------------------------------------------------------------------------------------------");

        //6. Check Size of HashMap
        System.out.println("Check size of hashmap is " + mainmap.size());
        System.out.println("Q6-Done--------------------------------------------------------------------------------------------------------------------");

        //7. Remove elements

        Map<String, Customer> customerMap = mainmap.get("Map A");
        if (customerMap != null) {
            Customer removeCustomer = customerMap.get("Z02");
            customerMap.remove("Z02", removeCustomer);
            System.out.println("Removed customer is " + removeCustomer);
        } else {
            System.out.println("Could not find inner map");
        }

        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            System.out.println(outerMap.getKey() + " => " + outerMap.getValue());
        }

        System.out.println("Q7-Done--------------------------------------------------------------------------------------------------------------------");

        //8. Create two Hashsets's of object customer( number , name , phoneNo ) where in each list one customer has the same phoneNo


        mainmap.put("Map B", customerMap2);
        for (Map.Entry<String, Map<String, Customer>> outerMap : mainmap.entrySet()) {
            System.out.println(outerMap.getKey() + " => " + outerMap.getValue());
        }
        System.out.println("Q8-Done--------------------------------------------------------------------------------------------------------------------");

        //8(i) Loop through these two Hashsets's and find the matching customer based on their phoneNo and print that customer.
        Map<String, Customer> myMapA = mainmap.get("Map A");
        Map<String, Customer> myMapB = mainmap.get("Map B");

        for (Map.Entry<String, Customer> mapA : myMapA.entrySet()) {
            Customer customer1 = mapA.getValue();
            for (Map.Entry<String, Customer> mapB : myMapB.entrySet()) {
                Customer customer2 = mapB.getValue();
                if (customer1.getPhoneNo().equals(customer2.getPhoneNo())) {
                    System.out.println(customer1);
                }
            }
        }
        System.out.println("Q8(i)-Done--------------------------------------------------------------------------------------------------------------------");


    }
}
