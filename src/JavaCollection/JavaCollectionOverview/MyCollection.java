package JavaCollection.JavaCollectionOverview;

import java.util.*;
//import java.util.*;
public class MyCollection
{
   // Collection collection = new ArrayList();

    public static void doSomething(Collection c) {
        Iterator ite = c.iterator();
        while(ite.hasNext()) {
            Object object = ite.next();
            System.out.println(object);  // Or any action
        }
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        System.out.println("Hash list items are : ");
        set.add("havana");
        set.add("Cassidy");
        set.add("cassidy");
        set.add("$10");
        set.add("50 ways");
        set.add("somersault");
       MyCollection.doSomething(set);
        //System.out.println(set);
        System.out.println("--------------------------------------");
        System.out.println("Array list items are : ");
        List list = new ArrayList();
        list.add("50 cent");
        list.add("Eminem");
        list.add("Travis");
        list.add("Tyler");
        list.add("Tupac");
        list.add("Rick Ross");
        MyCollection.doSomething(list);

        // Add Element to Collection

        String anElement = "an element";
       // Collection collection = new HashSet();

        boolean didCollectionChange = set.add(anElement);
        System.out.println(didCollectionChange);

        System.out.println("Set 2 :  " + set);

        //System.out.println(didCollectionChange);
        boolean didSetChange = set.add("havana");
        System.out.println("Set 3 : " + didSetChange);
        System.out.println(set);

        boolean didListChange = list.add("50 cent");
        System.out.println(list);
        System.out.println(didListChange);


        // Remove Element From Collection

        boolean wasElementRemoved = set.remove("Way");
        System.out.println(set);
        System.out.println(wasElementRemoved);


        //Add Collection of Objects to Collection

        list.addAll(set);
        System.out.println(list);

        // Remove Collection of Elements From Collection
        set.removeAll(set);
        System.out.println(set);

    }

}
