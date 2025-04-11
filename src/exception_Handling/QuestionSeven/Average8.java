package exception_Handling.QuestionSeven;

public class Average8 {

    public static void main(String[] args) {
        try { // (1)
            // Call printAverage with denominator 0 to simulate an error
            printAverage(100, 0); // (2)
        } catch (IntegerDivisionByZero idbze) { // (3)
            // Catch the custom checked exception
            idbze.printStackTrace(); // Print the full stack trace
            System.out.println("Exception handled in main().");
        } finally { // (4)
            // Always runs, regardless of exception
            System.out.println("Finally done in main().");
        }

        System.out.println("Exit main()."); // (5)
    }

    // Method declares that it can throw IntegerDivisionByZero
    public static void printAverage(int totalSum, int totalNumber)
            throws IntegerDivisionByZero { // (6)
        // Attempt to compute average
        int average = computeAverage(totalSum, totalNumber);

        // This line will not execute if an exception is thrown
        System.out.println("Average = " +
                totalSum + " / " + totalNumber + " = " + average);

        System.out.println("Exit printAverage()."); // (7)
    }

    // Also declares the potential for IntegerDivisionByZero to be thrown
    public static int computeAverage(int sum, int number)
            throws IntegerDivisionByZero { // (8)
        System.out.println("Computing average.");

        // Manually throw custom checked exception if number is 0
        if (number == 0) // (9)
            throw new IntegerDivisionByZero("Integer Division By Zero");

        return sum / number; // (10) ← Won’t be reached if exception is thrown
    }



    /*

 (2) printAverage(100, 0) is called from main()
(6)	printAverage() calls computeAverage(100, 0)
(8)	computeAverage() checks if number == 0
(9)	Since denominator is 0, throws custom checked exception IntegerDivisionByZero
 (10)	Skipped due to exception
 --Exception propagates from computeAverage() → printAverage() → main()
(3)	main() catches the exception in catch (IntegerDivisionByZero idbze)
Stack trace is printed and custom message is shown
(4)	finally block in main() executes
(5)	"Exit main()." is printed as the last step

    */

}
