package Generics_Pratice_Questions.Inventory_Management;

import java.util.*;
import java.util.function.Predicate;
/*
*
Inventory Management System with Wildcards
Scenario:
You're building an inventory system for a warehouse.
  There are different types of Item objects like Electronic, Furniture, and Perishable.
  You have a method that should calculate total value for any list of Item or its subtypes.

 Task:

Create a class hierarchy: Item → Electronic, Furniture, etc.
Implement a method:

public static double calculateTotalValue(List<? extends Item> items)

Ensure it works for List<Electronic> and List<Furniture> without casting.

Add a generic method to filter items by a condition using Predicate<T>.


* */
public class InventoryApp
{
    public static void main(String[] args)
    {

        List<Electronic> electronics  = List.of(
                new Electronic("Laptop" , 1200.00) ,
                new Electronic("Smartphone" , 800.00)
        );

        List<Furniture> furnitures = List.of(

                new Furniture("Chair" , 150.00 ),
                new Furniture("Table" , 300.00 )
                );


        // Total Value for Electronics

        System.out.println("Total value for electronics is : " + InventoryUtils.calculateTotalValue(electronics));

        // Total Value for furniture

        System.out.println("Total value for furniture is " + InventoryUtils.calculateTotalValue(furnitures));


        // Filtering expensive electronics

        List<Electronic> expensiveElectronics = InventoryUtils.filterItems(electronics , e -> e.getPrice() > 1000);


        System.out.println("Expensive Electronics");

        for (Electronic e : expensiveElectronics)
        {
            System.out.println(e.getName() + " - $" + e.getPrice());
        }


    }
}
