package JavaCollection.JavaSet;

public class Employee
{


    private String name;
    private int employeeId;


    public Employee(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    // Getters
    public String getName() { return name; }
    public int getEmployeeId() { return employeeId; }

    // Override equals and hashCode for Set behavior
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }

        if(!(o instanceof Employee))
        {
            return false ;
        }

        Employee other = (Employee) o ;
        return this.employeeId == other.employeeId;
    }

    public int hashCode()
    {

        return Integer.hashCode(employeeId);
    }
    public String toString() {
        return " Employee : " + name +  " ,  ID : " + employeeId ;
    }



}
