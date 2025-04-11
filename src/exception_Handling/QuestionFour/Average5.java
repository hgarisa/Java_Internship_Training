package exception_Handling.QuestionFour;

public class Average5 {

    public static void main(String[] args) {
        // Call printAverage with totalNumber = 0 → will cause divide-by-zero error
        printAverage(100, 0); // (1)

        // This line will NOT execute because the exception is unhandled in printAverage()
        System.out.println("Exit main()."); // (2)
    }

    public static void printAverage(int totalSum, int totalNumber) {
        try { // (3)
            // Attempt to compute average
            int average = computeAverage(totalSum, totalNumber); // (4)

            // This line will NOT execute if an exception is thrown in computeAverage
            System.out.println("Average = " + // (5)
                    totalSum + " / " + totalNumber + " = " + average);
        } finally { // (6)
            // This block ALWAYS executes, even if an exception is thrown
            System.out.println("Finally done.");
        }

        // This line will NOT execute if an exception occurs and is not caught
        System.out.println("Exit printAverage()."); // (7)
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // (8)

        // Division by zero → throws ArithmeticException (unchecked)
        return sum / number; // (9)
    }


    /*

(1)	Calls printAverage(100, 0) with zero denominator.
(3)	Enters the try block in printAverage.
(4)	Calls computeAverage(100, 0).
(8)	Prints "Computing average."
(9)	Throws ArithmeticException due to division by zero.
 (5)	Skipped — exception occurred before this line.
 (6)	finally block runs: prints "Finally done."
 (7)	Skipped — exception propagates and exits method before reaching this line.
 (2)	Skipped — since exception was not caught, the program terminates abnormally after finally.

   */
}
