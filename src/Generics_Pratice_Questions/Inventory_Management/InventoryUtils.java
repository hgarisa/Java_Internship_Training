package Generics_Pratice_Questions.Inventory_Management;

import java.util.*;
import java.util.function.Predicate;

public class InventoryUtils
{

    //  Wildcard usage: accepts any subclass of Item

    public static double calculateTotalValue(List<? extends Item> items)
    {
        double total = 0.0;
        for (Item item: items)
        {
            total = total + item.getPrice();
        }
        return total;
    }

   // Generic method : filters items using a custom condition

    public static <T> List<T> filterItems(List<T> items , Predicate<T> condition)
    {

        List<T> filtered = new ArrayList<>();

        for (T item : items)
        {
            if (condition.test(item))  // True
            {
                filtered.add(item);
            }

        }

       return filtered;
    }




}
