package JavaCollection.JavaList;

import java.util.List;
import java.util.*;

public class PersonListExample
{

    public static void main(String[] args) {
        List<Person> mypeople = new ArrayList<>();

        // Add elements to the end

        mypeople.add(new Person("Hrudhay" , 23));
        mypeople.add(new Person("Alice", 30));
        mypeople.add(new Person("Bob", 25));
        mypeople.add(new Person("Charlie", 28));


        // Add null value

        mypeople.add(null);

        // Add at specific index
        mypeople.add(1 , new Person("Diana", 22));  // Insert at index 1

        // Add all from another list

        List<Person> newPeople = new ArrayList<>();
        newPeople.add( new Person("Danny" , 23));
        newPeople.add(new Person("Eve", 35));
        newPeople.add(new Person("Frank", 27));

        mypeople.addAll(newPeople);

        // Print the list

        for ( Person person : mypeople )
        {
            System.out.println(person);

        }




    }
}
