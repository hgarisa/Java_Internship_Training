package Java_Reflection.Dynamic_Report_Formatter;
import java.io.*;
import java.util.*;
/*

Real-World Scenario:
You work in a FinTech company that processes large volumes of financial transactions.
The company wants to generate dynamic reports on these transactions in different formats like:

CSV

JSON

Plain Text

However, the formatting classes should be loaded dynamically using Reflection
(to support plugins in future), and the report output should be written to disk using Java I/O.

* */

public class ReportGeneratorApp {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("TXN001", "Deposit", 1500.75),
                new Transaction("TXN002", "Withdrawal", 800.25),
                new Transaction("TXN003", "Transfer", 420.00)
        );

        try (BufferedReader reader = new BufferedReader(new FileReader("report_formatters.txt"))) {
            String className;
            while ((className = reader.readLine()) != null) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    if (instance instanceof ReportFormatter) {
                        ReportFormatter formatter = (ReportFormatter) instance;
                        String report = formatter.format(transactions);

                        String outputFileName = className.substring(className.lastIndexOf(".") + 1) + ".txt";
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                            writer.write(report);
                        }

                        System.out.println("Report written using " + className + " to " + outputFileName);
                    } else {
                        System.err.println(className + " is not a ReportFormatter!");
                    }
                } catch (Exception e) {
                    System.err.println("Failed to load or instantiate class: " + className);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading formatter file: " + e.getMessage());
        }
    }
}
