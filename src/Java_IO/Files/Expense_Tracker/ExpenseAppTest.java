package Java_IO.Files.Expense_Tracker;

/*
* Scenario:
You're building a utility that reads daily expense logs from a file
and writes a formatted monthly summary report to a new file.

* */
public class ExpenseAppTest {
    public static void main(String[] args) throws Exception {
        String inputFile = "expense_logs.txt";     // Format: 2025-07-01,Food,120.50
        String outputFile = "monthly_expense_summary.txt";

        ExpenseReportGenerator generator = new ExpenseReportGenerator();
        generator.generateReport(inputFile, outputFile);
    }
}

