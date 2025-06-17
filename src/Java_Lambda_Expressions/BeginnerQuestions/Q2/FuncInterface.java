package Java_Lambda_Expressions.BeginnerQuestions.Q2;


// Java program to demonstrate lambda expressions
// to implement a user defined functional interface.

// A sample functional interface (An interface with
// single abstract method
public interface FuncInterface
{

    // An Abstract method

    void abstractFun(int x);

    // A non-abstract method


      default void normalfun()
    {
        System.out.println("Hello");
    }

}
