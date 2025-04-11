package exception_Handling.QuestionOne;

public class Average2 {

    public static void main(String[] args) {
        // Call printAverage with a total sum and zero count
        printAverage(100, 0); // (1)
        // This will trigger a divide-by-zero situation inside computeAverage()

        System.out.println("Exit main()."); // (2)
        // This line runs after exception is caught and handled in printAverage()
    }

    public static void printAverage(int totalSum, int totalNumber) {
        try { // (3)
            // Attempt to compute the average
            int average = computeAverage(totalSum, totalNumber); // (4)

            // If successful (no exception), print the result
            System.out.println("Average = " + // (5)
                    totalSum + " / " + totalNumber + " = " + average);

        } catch (ArithmeticException ae) { // (6)
            // Catch divide-by-zero error (ArithmeticException)
            ae.printStackTrace(); // (7)
            // Print the stack trace for debugging purposes

            System.out.println("Exception handled in printAverage()."); // (8)
            // Log message indicating exception was caught and handled here
        }

        // Always prints, regardless of whether exception occurred or not
        System.out.println("Exit printAverage()."); // (9)
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // (10)

        // Attempt division (can cause ArithmeticException if number == 0)
        return sum / number; // (11)
    }


/*(1)	printAverage(100, 0) is called → division by 0 will occur.
            (3)	try block begins to monitor for exceptions.
            (4)	computeAverage(100, 0) is called.
            (10)	"Computing average." is printed.
            (11)	Division 100 / 0 triggers an ArithmeticException.
            (6)	Exception is caught by catch (ArithmeticException ae).
            (7)	Stack trace of the exception is printed.
            (8)	A friendly message indicates the exception was handled.
            (9)	Regardless of error, "Exit printAverage()" is printed.
            (2)	Finally, main continues and prints "Exit main()".*/



}
