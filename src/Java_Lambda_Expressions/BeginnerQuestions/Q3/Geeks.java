package Java_Lambda_Expressions.BeginnerQuestions.Q3;

import java.util.ArrayList;

public class Geeks
{
    public static void main(String[] args)
    {
        // Q3.1 Lambda with Zero Parameter
        //Lambda Expression with Zero Parameters

        ZeroParameter zeroParameterLambda = () ->{System.out.println("This is a non-zero lambda expression");};

        // Invoke the method
        zeroParameterLambda.display();
        System.out.println("Q(3.1)-Done--------------------------------------------------");
        // Q3.1 Done


        // Q3.2 Lambda with a Single Parameter


        // Creating an ArrayList with elements
        // {1, 2, 3, 4}


        ArrayList <Integer> al =  new ArrayList<Integer>();

        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        // Q3.2.(1) Using lambda expressions to print all elements of al

        System.out.println("Elements of the Array List are : ");

        al.forEach(n -> System.out.println(n));
        System.out.println("Q3.2.(1)-Done----------------------------------------------------");

        // Q3.2.(2) Using lambda expression to print even number of  elements
        // of al

        System.out.println("Even Number of elements");
        al.forEach( n -> {if (n % 2 == 0) System.out.println(n); });

        System.out.println("Q3.2.(2)-Done--------------------------------------------------------------");


        //Q3.3 Lambda Expression with Multiple Parameters






    }
}
