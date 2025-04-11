package exception_Handling.QuestionFive;

public class Average6 {

    public static void main(String[] args) {
        // Call printAverage() with totalNumber = 0 → will cause division by zero
        System.out.println("Average: " + printAverage(100, 0)); // (1)

        // This line will NOT execute if the exception is unhandled
        System.out.println("Exit main()."); // (2)
    }

    public static int printAverage(int totalSum, int totalNumber) {
        int average = 0;

        try { // (3)
            // This line will throw ArithmeticException if totalNumber = 0
            average = computeAverage(totalSum, totalNumber); // (4)

            // This line is skipped if exception is thrown
            System.out.println("Average = " +
                    totalSum + " / " + totalNumber + " = " + average); // (5)

            return average; // (6) ← Will be overridden if `finally` executes

        } finally { // (7)
            System.out.println("Finally done.");

            // Even if an exception occurred or return was called above,
            // this return *overrides* everything and becomes the final return value
            return average * 2; // (8) ← Overrides return from try
        }
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // (9)

        // Division by zero will trigger ArithmeticException
        return sum / number; // (10)
    }



    /*


  (1) Calls printAverage(100, 0)
(3)	Enters try block
(4)	Calls computeAverage(100, 0)
(9)	Prints "Computing average."
(10)	Tries to execute 100 / 0 → throws ArithmeticException
No Catch Block	Exception is not caught, so control goes to finally
(7)	finally block is executed
average = 0	Since computeAverage() failed, average is still 0
(8)	Returns 0 * 2 = 0 from finally
Exception is still active	Even though finally has a return, the ArithmeticException is suppressed, and no error is shown — this is dangerous behavior
(1)	Prints "Average: 0" unexpectedly
(2)	Prints "Exit main()." ← Program does not crash


     */
}
