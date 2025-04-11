package exception_Handling.QuestionSix;

public class Average7 {

    public static void main(String[] args) {
        try { // (1)
            // Try calling method that may throw an ArithmeticException
            printAverage(100, 0); // (2)
        } catch (ArithmeticException ae) { // (3)
            // Handle ArithmeticException thrown from computeAverage()
            ae.printStackTrace(); // (4) → shows error location
            System.out.println("Exception handled in main()."); // (5)
        } finally {
            System.out.println("Finally in main()."); // (6) → always runs
        }

        System.out.println("Exit main()."); // (7) → runs after try-catch-finally
    }

    public static void printAverage(int totalSum, int totalNumber) {
        try { // (8)
            // Try to compute average — will throw if totalNumber is 0
            int average = computeAverage(totalSum, totalNumber); // (9)

            // This line is skipped if an exception is thrown above
            System.out.println("Average = " + // (10)
                    totalSum + " / " + totalNumber + " = " + average);

        } catch (IllegalArgumentException iae) { // (11)
            // This block will NOT catch ArithmeticException
            iae.printStackTrace(); // (12)
            System.out.println("Exception handled in printAverage()."); // (13)

        } finally {
            System.out.println("Finally in printAverage()."); // (14) → always runs
        }

        // This line will NOT run if an exception is thrown and not caught here
        System.out.println("Exit printAverage()."); // (15)
    }

    public static int computeAverage(int sum, int number) {
        System.out.println("Computing average."); // Shown before exception

        if (number == 0) // (16)
            // Throw custom ArithmeticException instead of letting division trigger it
            throw new ArithmeticException("Integer division by 0"); // (17)

        return sum / number; // (18) ← Won’t run if number is 0
    }


    /*


    (2)	printAverage(100, 0) is called from main()
(8)	Enters the try block inside printAverage()
(9)	Calls computeAverage(100, 0)
(16)	Checks if number == 0
(17)	Throws ArithmeticException("Integer division by 0")
(11)	catch (IllegalArgumentException) does not match → skipped
(14)	finally block runs → prints "Finally in printAverage()."
 (15)	Skipped because unhandled exception leaves method
(3)	Control goes to main()'s catch (ArithmeticException)
(4)	Stack trace is printed
(5)	"Exception handled in main()." is printed
(6)	finally block in main() runs: "Finally in main()."
(7)	"Exit main()." is printed → program ends normally

    */



}
