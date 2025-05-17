package JavaCollection.JavaList;

import java.util.*;
public class Person
{

//    private String name ;
//    private int age ;

    public String name ;
    public int age ;

    // Constructor

    public Person(String name , int age )
    {

        this.name = name;
        this.age = age;

    }

   /* public String toString()
    {

        return  "Name is : " + name + " , " +  " Age is " + age ;
    }*/


    // Getters
 /*   public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }*/

    public String toString()
    {

        return  name +  " : "  + age ;

    }

}
