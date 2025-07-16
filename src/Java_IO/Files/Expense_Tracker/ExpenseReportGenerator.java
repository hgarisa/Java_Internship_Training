package Java_IO.Files.Expense_Tracker;

import java.io.*;
import java.util.*;

/*
 * Reads daily expense logs and generates a monthly summary report by category.
 */
public class ExpenseReportGenerator {
    public void generateReport(String inputPath, String outputPath) throws IOException {
        Map<String, Double> categoryTotals = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip blank lines

                String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.err.println("Invalid line skipped: " + line);
                    continue; // Skip invalid format
                }

                String category = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());

                categoryTotals.merge(category, amount, Double::sum);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            writer.println("Monthly Expense Summary Report:");
            for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                writer.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println("Expense report generated at: " + outputPath);
    }
}

