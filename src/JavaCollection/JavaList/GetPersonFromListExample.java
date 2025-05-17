package JavaCollection.JavaList;

import java.util.ArrayList;
import java.util.List;

public class GetPersonFromListExample
{
    public static void main(String[] args) {


        List<Person> mypersonlist = new ArrayList<>();

        // Add people to the list

//        mypersonlist.add(new Person("Hatt" , 23));
//        mypersonlist.add(new Person("Bob", 25));
//        mypersonlist.add(new Person("Charlie", 28));

        // Get elements by index

//        Person po = mypersonlist.get(0);  // First person
//        Person p1 = mypersonlist.get(1); // Second person
//        Person p2 = mypersonlist.get(2); // Third person


        // Print their details using getters

//        System.out.println("Person 0 : " + po.getName() + " ,  " +  po.getAge());
//        System.out.println("Person 1 : " + p1.getName() + " ,  " +  p1.getAge());
//        System.out.println("Person 2 : " + p2.getName() + " ,  " +  p2.getAge());


        // Print their details directly (no getters)


//        System.out.println("Person 0: " + po.name + " , " +  po.age);
//        System.out.println("Person 1: " + p1.name + " , " + p1.age);
//        System.out.println("Person 2: " +  p2.name +  " , " + p2.age);



        // Create specific objects

//        Person alice1 = new Person("Alice" , 30);
//        Person bob = new Person("Bob", 25);
//        Person alice2 = new Person("Alice" , 30); // same data, different object


        // Add to list

//        mypersonlist.add(alice1); // index 0
//        mypersonlist.add(bob); // index 1
//        mypersonlist.add(alice1); // index 2
//        mypersonlist.add(alice2); // index 3
//        mypersonlist.add(alice1); // index 4

        // These will only work if you pass the actual object reference


//        int firstindex = mypersonlist.indexOf(alice1);
//        int secondindex = mypersonlist.lastIndexOf(alice1);
//
//        System.out.println("First Index of Alice 1 : " + firstindex);
//        System.out.println("Last index of Alice 1 : " + secondindex);


//        // Add a person
//        Person p1 = new Person("Alice" , 30);
//        Person p2 = new Person("John" , 20);
//        // Same name and age but different object
//        Person p3 = new Person("Alice", 30);
//        mypersonlist.add(p1);
//        mypersonlist.add(p2);
//        mypersonlist.add(p1);
//
//        boolean contains = mypersonlist.contains(p3);
//
//        for ( Person person: mypersonlist)
//        {
//            System.out.println(person.getName() + " , " + person.getAge());
//        }
//
//        System.out.println("Contains person : " + contains);
//



        Person alice = new Person("Alice" , 30);
        Person bob  = new Person("Bob" , 20);

        Person charlie  = new Person("Charlie" , 28);

        // Add people to list

        mypersonlist.add(alice);
        mypersonlist.add(bob);
        mypersonlist.add(charlie);
        System.out.println("Original list:" + mypersonlist);

        // Remove by object (works only if it's the exact object reference)




    }


}
