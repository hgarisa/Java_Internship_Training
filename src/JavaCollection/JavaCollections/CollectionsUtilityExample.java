package JavaCollection.JavaCollections;

import java.util.*;
public class CollectionsUtilityExample
{
    public static void main(String[] args)
    {


        // -------------------------------
        // 1. addAll() - Add multiple items
        // -------------------------------


//        List<String> mainlist = new ArrayList<>();
//        Collections.addAll(mainlist , "Banana" , "Apple" , "Mango");
//        System.out.println("After addAll(): " + mainlist);


        // -------------------------------
        // 2. binarySearch() - Find index (must sort first)
        // -------------------------------

//        Collections.sort(mainlist); // Required before binarySearch
//        System.out.println("Sorted list: " + mainlist);
//
//        int index = Collections.binarySearch(mainlist , "Mango");
//        System.out.println(" Index of Mango " + index);

        // -------------------------------
        // 3. copy() - Copy elements from one list to another
        // -------------------------------
//        List<Integer> sourceList = new ArrayList<>();
//        sourceList.add(1);
//        sourceList.add(2);
//        sourceList.add(3);
//
//        List<Integer> destinationList = new ArrayList<>();
//        destinationList.add(4);
//        destinationList.add(5);
//        destinationList.add(6);
//        destinationList.add(7); // Destination list is larger than source
//
//        Collections.copy(destinationList, sourceList);
//
//        System.out.println("Source List: " + sourceList);
//        System.out.println("Destination List: " + destinationList);


        // -------------------------------
        // 4. reverse() - Reverse list elements
        // -------------------------------

//        Collections.reverse(destinationList);
//        System.out.println("Destination list after reverse(): " + destinationList);



        // -------------------------------
        // 5. shuffle() - Random order
        // -------------------------------

//List<String> lang  = new ArrayList<>();
//Collections.addAll(lang, "Java", "Python", "C++", "Go", "Rust");
//System.out.println("Original list: " + lang);
//
//        Collections.shuffle(lang);
//        System.out.println("After shuffle: " + lang);


        // -------------------------------
        // 2. sort() - Alphabetical order
        // -------------------------------
//        Collections.sort(lang);
//        System.out.println("After sort: " + lang);

        // -------------------------------
        // 4. min() - Smallest (alphabetically first) element
        // -------------------------------

//        String minvalue = Collections.min(lang);
//        System.out.println("Minimum value: " + minvalue);
    //    System.out.println(Collections.min(lang));



        // -------- max() --------

        List<String> numbers = new ArrayList<>();
        Collections.addAll(numbers, "1", "2", "3");
        String max = Collections.max(numbers);
        System.out.println("Max value: " + max);  // Output: 3

         // -------- replaceAll() --------

       List<String> letters =  new ArrayList<>();
       Collections.addAll(letters, "A", "B", "A");
       boolean replaced = Collections.replaceAll(letters , "A" , "C");
        System.out.println("List after replaceAll(): " + letters);  // Output: [C, B, C]
        System.out.println("Was anything replaced? " + replaced);   // Output: true

        // -------- unmodifiableSet() --------


//        Set<String> modifiableSet = new HashSet<>();
//        modifiableSet.add("X");
//        modifiableSet.add("Y");
//
//        Set<String> immutableSet = Collections.unmodifiableSet(modifiableSet);
//        System.out.println("Unmodifiable Set: " + immutableSet);
//
//        immutableSet.add("C");
//        System.out.println(immutableSet);


    }


}
