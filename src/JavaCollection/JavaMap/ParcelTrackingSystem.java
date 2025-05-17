package JavaCollection.JavaMap;

import java.util.*;
import java.util.stream.Stream;

public class ParcelTrackingSystem
{
    public static void main(String[] args)
    {

        // Create a Map to store trackingNumber -> Parcel

        Map<String, Parcel> trackingMap = new HashMap<>();

        // Add parcels using .put()
        trackingMap.put("TRK001", new Parcel("Alice", "123 Main St", false));
        trackingMap.put("TRK002", new Parcel("Bob", "456 Oak Ave", true));
        trackingMap.put("TRK003", new Parcel("Charlie", "789 Elm Rd", false));

        // Replace parcel info (same key)
        trackingMap.put("TRK002", new Parcel("Bob", "456 Oak Ave", false)); // updated delivery status

        // Use get() to retrieve a specific parcel
        Parcel p1 = trackingMap.get("TRK001");
        System.out.println("Tracking TRK001: " + p1);


        // Use getOrDefault() to handle unknown tracking numbers
        Parcel p2 = trackingMap.getOrDefault("TRK999" , new Parcel("Unknown", "N/A", false));
        System.out.println("Tracking TRK999: " + p2);

        // Check if a key exists

        boolean hasTRK002 = trackingMap.containsKey("TRK002");
        System.out.println("Does TRK002 exist ? " + hasTRK002);

        // Check if a specific Parcel value exists
        boolean hasSpecificParcel = trackingMap.containsValue(new Parcel("Charlie", "789 Elm Rd", false));
        System.out.println("Is Charlie's parcel in the map? " + hasSpecificParcel); // likely false unless equals() is overridden

        // Use keySet to loop through all parcels

        System.out.println("\nAll Parcels:");
        for (String trackingNum : trackingMap.keySet()) {
            System.out.println("Tracking #: " + trackingNum + " => " + trackingMap.get(trackingNum));
        }


        System.out.println("=== Using Key Iterator ===");
        Iterator<String> iterator = trackingMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Parcel parcel = trackingMap.get(key);
            System.out.println(parcel);
        }

        System.out.println("\n=== Using For-Each Loop ===");
        for (String key : trackingMap.keySet()) {
            Parcel parcel = trackingMap.get(key);
            System.out.println(parcel);
        }

        System.out.println("\n=== Using Key Stream ===");
        Stream<String> stream = trackingMap.keySet().stream();
        stream.forEach(key -> {
            Parcel parcel = trackingMap.get(key);
            System.out.println(parcel);
        });




    }
}
