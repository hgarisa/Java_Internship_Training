package Java_String_Class_Methods.Q1;
/*

Employee Email ID Generation System

        Problem Statement:
        You are building an internal email generation system for employees.
        Based on employee's name, department, and company name, generate their official email IDs.

        The format of email should be:
        firstname.lastname@department.company.com

        All names should be converted to lowercase and trimmed before generating email.

        Handle cases where name may have multiple spaces.

        Validate that names do not contain any digits or special characters except spaces.

*/

import java.util.*;
import java.util.regex.*;
public class Employee
{

    private String firstName;
    private String lastName;
    private String department;
    private String company;

    public Employee(String firstName, String lastName, String department, String company) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.department = department.trim();
        this.company = company.trim();
    }

    // Method to generate email ID
    public String generateEmail() {
        // Clean extra spaces and convert to lowercase
        String cleanedFirstName = firstName.replaceAll("\\s+", " ").toLowerCase();
        String cleanedLastName = lastName.replaceAll("\\s+", " ").toLowerCase();

        // Validate names
        if (!isValidName(cleanedFirstName) || !isValidName(cleanedLastName)) {
            throw new IllegalArgumentException("Invalid characters in name.");
        }

        return cleanedFirstName + "." + cleanedLastName + "@" +
                department.toLowerCase() + "." +
                company.toLowerCase() + ".com";
    }

    // Method to validate names
    private boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Flow: Create Employee -> Generate Email
        Employee emp = new Employee(" John  ", "  Doe  ", "IT", "ExampleCorp");
        System.out.println("Generated Email: " + emp.generateEmail());
    }
}
