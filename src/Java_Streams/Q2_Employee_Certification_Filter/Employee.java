package Java_Streams.Q2_Employee_Certification_Filter;

import java.util.*;

// Employee class representing employees in HR system

public class Employee
{

    private String employeeId;
    private String fullName;
    private String department;
    private List<String> certifications;

    public Employee(String employeeId, String fullName, String department, List<String> certifications) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
        this.certifications = certifications;
    }

    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public List<String> getCertifications() { return certifications; }


}
