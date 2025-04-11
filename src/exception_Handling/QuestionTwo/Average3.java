package exception_Handling.QuestionTwo;

public class Average3 {

    public static void main(String[] args) {
        try { // (1)
            // Call method with totalNumber = 0 → will cause ArithmeticException
            printAverage(100, 0); // (2)
        } catch (ArithmeticException ae) { // (3)
            // This catches the ArithmeticException not handled in printAverage()
            ae.printStackTrace(); // (4)
            System.out.println("Exception handled in main()."); // (5)
        }

        // This line runs whether or not exception occurs
        System.out.println("Exit main()."); // (6)
    }

    public static void printAverage(int totalSum, int totalNumber) {
        try { // (7)
            // Call computeAverage which will perform division
            int average = computeAverage(totalSum, totalNumber); // (8)

            // This line won't run if exception is thrown
            System.out.println("Average = " + // (9)
                    totalSum + " / " + totalNumber + " = " + average);

        } catch (IllegalArgumentException iae) { // (10)
            // This block does NOT catch ArithmeticException
            iae.printStackTrace(); // (11)
            System.out.println("Exception handled in printAverage()."); // (12)
        }

        // Always runs after try-catch
        System.out.println("Exit printAverage()."); // (13)
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // (14)

        // If number is zero, this throws ArithmeticException (unchecked)
        return sum / number; // (15)
    }


/*(1)	A try block in main() is set up to catch ArithmeticException.
            (2)	Calls printAverage(100, 0) with zero as denominator.
            (7)	A try block is entered inside printAverage().
            (8)	Calls computeAverage(100, 0).
            (14)	Prints "Computing average."
            (15)	Throws ArithmeticException because of division by zero.

Exception Propagation Since only IllegalArgumentException is caught in line (10), the ArithmeticException is not
 caught in printAverage() and propagates to the caller — main().
(3)	Exception is caught by main()'s catch (ArithmeticException ae).
(4)	Stack trace is printed.
(5)	"Exception handled in main()." is printed.
(13)	"Exit printAverage()." is still printed after exception (from printAverage()'s try-catch).
(6)	"Exit main()." is printed after all exception handling is complete.


            */

}
