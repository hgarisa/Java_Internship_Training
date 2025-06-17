package JavaCollection.JavaMap;


import java.util.*;
public class InventoryManager
{
    public static void main(String[] args)
    {


        Map<String , InventoryItem> inventoryMap = new HashMap<>();

        // Initial item
        inventoryMap.put("ITEM1001" , new InventoryItem("Notebook", 10));

        // (1️) compute() – update quantity or remove if null


        inventoryMap.compute("ITEM1001" , (itemName , NoOfitem) -> {
            if(NoOfitem != null)
            {

                NoOfitem.increaseQuantity(5);  // Add 5 more
                return NoOfitem ;
            }

            return null;
        });


        //System.out.println(inventoryMap);

        // ✅ 2. computeIfAbsent() – add item only if it doesn't exist

        inventoryMap.computeIfAbsent("ITEM2002" , (key) -> new InventoryItem("Pen" , 50));


        //System.out.println(inventoryMap);

        // ✅ 3. computeIfPresent() – decrease quantity if present



        inventoryMap.computeIfPresent("ITEM1001" , (itemName , NoofItem) -> {

            NoofItem.decreaseQuantity(3);

            return NoofItem;

        });

       // System.out.println(inventoryMap);


        // ✅ 4. merge() – add quantity to existing item, or insert if not there


        inventoryMap.merge("ITEM2002" , new InventoryItem("Pen", 20) , (existingNoofItem, newNoofItem) ->
        {

            existingNoofItem.increaseQuantity(newNoofItem.getQuantity());
            return existingNoofItem;

        });

        // System.out.println(inventoryMap);

    // Print Final State

        System.out.println("📦 Final Inventory:");

        for (Map.Entry<String , InventoryItem> entry : inventoryMap.entrySet())
        {

            System.out.println(entry.getKey() +  "=>" + entry.getValue() );
        }



    }


}
