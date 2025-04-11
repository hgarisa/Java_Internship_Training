package exception_Handling.QuestionThree;

public class Average4 {

    public static void main(String[] args) {
        // Call printAverage with denominator 0 to simulate a divide-by-zero error
        printAverage(100, 0); // (1)

        // This line will run after printAverage finishes, regardless of exception
        System.out.println("Exit main()."); // (2)
    }

    public static void printAverage(int totalSum, int totalNumber) {
        try { // (3)
            // Try to compute average
            int average = computeAverage(totalSum, totalNumber); // (4)

            // This will NOT execute if an exception is thrown above
            System.out.println("Average = " + // (5)
                    totalSum + " / " + totalNumber + " = " + average);

        } catch (ArithmeticException ae) { // (6)
            // This block catches divide-by-zero errors
            ae.printStackTrace(); // (7) - Prints full stack trace for debugging
            System.out.println("Exception handled in printAverage()."); // (8)

        } finally { // (9)
            // Always executes, whether or not an exception occurred
            System.out.println("Finally done.");
        }

        // This line runs after try-catch-finally finishes
        System.out.println("Exit printAverage()."); // (10)
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // (11)

        // This line will throw ArithmeticException if number is 0
        return sum / number; // (12)
    }


    /*

    (1)	Calls printAverage(100, 0), intending to compute 100 / 0.
(3)	Enters the try block.
(4)	Calls computeAverage(100, 0).
(11)	Prints "Computing average."
(12)	Throws ArithmeticException because of division by zero.
(6)	Catches the exception in catch (ArithmeticException ae).
(7)	Prints the stack trace.
(8)	Logs custom message: "Exception handled in printAverage()."
(9)	finally block executes: prints "Finally done."
(10)	After finally, prints "Exit printAverage()."
(2)	After returning from printAverage(), prints "Exit main()."


     */


}
