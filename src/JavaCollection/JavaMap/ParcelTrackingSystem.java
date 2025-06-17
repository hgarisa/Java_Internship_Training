package JavaCollection.JavaMap;

import java.util.*;
import java.util.stream.Stream;

public class ParcelTrackingSystem
{
    public static void main(String[] args)
    {

        Map<String, Parcel> parcelMap = new HashMap<>();

        // Add parcels
        parcelMap.put("TRK123", new Parcel("Alice", "123 Main St", false));
        parcelMap.put("TRK456", new Parcel("Bob", "456 Oak Ave", true));
        parcelMap.put("TRK003", new Parcel("Charlie", "789 Elm Rd", false));


        // 1. Get a parcel
        Parcel p1 = parcelMap.get("TRK123");
        System.out.println("Get parcel TRK123" + p1);

        // 2. Get or default

        Parcel defaultParcel = new Parcel("Unknown" , "Unknown" , false);
        Parcel p2 = parcelMap.getOrDefault("TRK999" , defaultParcel);
        System.out.println("Get parcel TRK999 or default " + p2);

        // 3. Check key

        boolean hasTracking = parcelMap.containsKey("TRK456");
        System.out.println("Contains tracking TRK456 ? " + hasTracking);

        // 4. Check value

        Parcel searchParcel = new Parcel("Alice", "123 Main St", false);

        boolean hasParcel = parcelMap.containsValue(searchParcel);

        System.out.println("Contains value  matching Alice’s parcel? " + hasParcel);


        System.out.println(" (1) Iterating using Key Iterator:");

        Iterator<String> ite = parcelMap.keySet().iterator();

        while (ite.hasNext())
        {
            String trackingNumber = ite.next();
            Parcel parcel = parcelMap.get(trackingNumber);
            System.out.println(trackingNumber + " => " + parcel);
        }

        System.out.println("\n (2) Iterating using For-Each Loop:");

        for ( String trackingNumber : parcelMap.keySet())
        {

            Parcel parcel = parcelMap.get(trackingNumber);
            System.out.println( trackingNumber + " => " + parcel);
        }


        System.out.println("\n (3) Iterating using Stream API:");


        Stream<String> stream = parcelMap.keySet().stream();

        stream.forEach(trackingNumber -> {

            Parcel parcel = parcelMap.get(trackingNumber);
            System.out.println(trackingNumber + " => " + parcel);

        } ) ;

         // (1️) Value Iterator
        System.out.println("Value Iterator:");

        Iterator<Parcel> valueIterator = parcelMap.values().iterator();
        while (valueIterator.hasNext())
        {

            Parcel parcel = valueIterator.next();
            System.out.println(parcel);

        }

        // ----------------------------
        // (2️) Value for  For-Each Loop
        System.out.println("\n Value For-Each Loop:");

        for ( Parcel parcel: parcelMap.values())
        {

            System.out.println(parcel);
        }

        // ----------------------------
        // (3️) Value Stream
        System.out.println("\n Value Stream:");
        Stream<Parcel> valueStream = parcelMap.values().stream();
        valueStream.forEach(System.out::println);

        // ----------------------------
        // (4️) Entry Iterator

        System.out.println("\n Entry Iterator (Key + Value):");
        Set<Map.Entry<String , Parcel>> entries = parcelMap.entrySet();
        Iterator<Map.Entry<String , Parcel>> entryIterator = entries.iterator();

        while (entryIterator.hasNext())
        {
            Map.Entry<String , Parcel> entry = entryIterator.next();
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // ----------------------------
        // (5️) Entry For-Each Loop

        System.out.println("\n Entry For-Each Loop (Key + Value):");

        for (Map.Entry<String , Parcel> entry : parcelMap.entrySet())
        {
            System.out.println(entry.getKey()  + " => " + entry.getValue() );
        }

        System.out.println("📦 Original Parcel Map:");
        printMap(parcelMap);

        // Step 2: Remove a specific entry by key

        parcelMap.remove("TRK456");
        System.out.println("After removing TRK456");
        printMap(parcelMap);

        // Step 3: Replace an entry (only works if key already exists)

        Parcel updatedParcel = new Parcel("Alice" , "123 Main St" , true);
        parcelMap.replace("TRK123" ,updatedParcel);
        System.out.println("After replacing TRK123 with updated delivered status");
        printMap(parcelMap);

        // Step 4: Count number of entries
        int count = parcelMap.size();
        System.out.println("Total number of parcels : " + count);


        // Step 5: Check if the map is empty

        System.out.println("Is the Map empty ?" + parcelMap.isEmpty());


        // Step 6: Clear all entries


        parcelMap.clear();
        System.out.println("\n🧹 After clearing the map:");
        System.out.println("📭 Is the map empty? " + parcelMap.isEmpty());



    }


    // Utility method to print key-value pairs in the map
    private static void printMap(Map<String , Parcel> map)
    {

        for (Map.Entry<String , Parcel> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

    }


}
