package Java_IO.Files.Employee_Feedback;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Generates a department-wise summary and writes to an output file.
 */
public class FeedbackSummaryGenerator {
    public void writeSummary(List<FeedbackEntry> entries, String outputFilePath) throws IOException {
        Map<String, List<String>> departmentMap = new LinkedHashMap<>();

        for (FeedbackEntry entry : entries) {
            departmentMap
                    .computeIfAbsent(entry.getDepartment(), k -> new ArrayList<>())
                    .add(entry.getName());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Map.Entry<String, List<String>> dept : departmentMap.entrySet()) {
                writer.write("Department: " + dept.getKey());
                writer.newLine();
                writer.write("Total Feedbacks: " + dept.getValue().size());
                writer.newLine();
                writer.write("Employees: " + String.join(", ", dept.getValue()));
                writer.newLine();
                writer.newLine();
            }
        }
        System.out.println("Feedback summary generated at: " + outputFilePath);
    }
}

