package Java_IO.Streams.CSV_Employee_Data;

import java.io.IOException;
import java.util.List;

/*

 Scenario
You're developing a system that:

Reads employee records from a .csv file

Parses and converts each line into an Employee object

Displays each employee in a formatted report


* */

/*
 * Entry point to test reading employee data from a CSV file.
 */


public class CSVEmployeeTest
{
    public static void main(String[] args) throws IOException {
        String csvFilePath = "employees.csv"; // File should exist at root

        EmployeeCSVReader reader = new EmployeeCSVReader();
        List<Employee> employees = reader.loadFromCSV(csvFilePath);

        System.out.println("\n Employee Report:");
        employees.forEach(System.out::println);
    }

}
