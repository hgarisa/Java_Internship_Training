package Java_Lambda_Expressions.BeginnerQuestions.Q5;


public class Main
{
    public static void main(String[] args)
    {

        GreetingService myservice = (s) -> System.out.println("Hello , " + s + "!") ;

        myservice.greet("Hrudhay");
    }
}
