package JavaCollection.JavaCollectionOverview;

import java.util.*;
public class CollectionUtil
{

    public static void main(String[] args) {

        // Retain All Elements From a Collection in Another Collection

   /*     Collection colA = new ArrayList(); // [A, B, C]
        colA.add("A");
        colA.add("B");
        colA.add("C");
        System.out.println(colA);
        Collection colB = new ArrayList(); // [1, 2, 3]
        colB.add("1");
        colB.add("2");
        colB.add("3");
        System.out.println(colB);


        Collection target = new HashSet();
        target.addAll(colA);
        target.addAll(colB);
        System.out.println(target);


        target.retainAll(colB);
        System.out.println(target);

        //Checking if a Collection Contains a Certain Element
        boolean containsElement = target.contains("1");
        System.out.println(containsElement);*/



        // ---------- addAll() ----------

        List<String> playlistA = new ArrayList<>();
        playlistA.add("Track1");
        playlistA.add("Track2");
        playlistA.add("Track3");

        List<String> playlistB = new ArrayList<>();
        playlistB.add("Track4");
        playlistB.add("Track5");

        System.out.println("Playlist A: " + playlistA);
        System.out.println("Playlist B: " + playlistB);

        playlistA.addAll(playlistB);  // Merge playlistB into playlistA
        System.out.println("Playlist A after addAll(B): " + playlistA);

        // ---------- removeAll() ----------

        List<String>  unwantedTracks = Arrays.asList("Track2" ,  "Track5");
        playlistA.removeAll(unwantedTracks);
        System.out.println("Playlist A after removeAll(unwantedTracks): " + playlistA);


        // ---------- retainAll() ----------

        List<String> allowedTracks = Arrays.asList("Track1", "Track6");
        playlistA.retainAll(allowedTracks);// Keep only allowed tracks
        System.out.println("Playlist A after retainAll(allowedTracks): " + playlistA);

        // ---------- contains() ----------

        boolean hasTrack1 = playlistA.contains("Track1");
        boolean hasTrack3 = playlistA.contains("Track3");
        System.out.println("\nContains 'Track1'? " + hasTrack1);
        System.out.println("Contains 'Track3'? " + hasTrack3);

        // ---------- containsAll() ----------

//        List<String> checkGroup = Arrays.asList("Track1" , "Track6");
//        boolean hasAll = playlistA.containsAll(checkGroup);
//        System.out.println("Contains all tracks [Track1, Track6]? " + hasAll);


        //Collection Size

//        int numberOfElements = playlistB.size();
//        System.out.println(numberOfElements);

        //Iterate a Collection

        // (1) way Using an Iterator

//       Iterator ite = playlistB.iterator();
//       while (ite.hasNext())
//       {
//
//           Object obj = ite.next();
//           System.out.println(obj);
//       }


        // (2) way  Using a for-each loop (simpler way)


        for(Object obj : playlistB)
        {

            System.out.println(obj);
        }

    }

}
