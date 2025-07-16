package Java_IO.Streams.CSV_Employee_Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Loads employee records from a CSV file and returns a list of Employee objects.
 */
public class EmployeeCSVReader
{
/*
  Reads CSV lines and converts them to Employee objects.
 */

    public List<Employee> loadFromCSV(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip blank  lines
                if (line.trim().isEmpty() || !line.contains(",")) continue;

                String[] parts = line.split(",");
                String name = parts[0].trim();
                String department = parts[1].trim();
                double salary = Double.parseDouble(parts[2].trim());

                employees.add(new Employee(name, department, salary));
            }
        }

        return employees;
    }


}
