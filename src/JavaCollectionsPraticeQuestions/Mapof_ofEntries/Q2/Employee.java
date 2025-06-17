package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q2;

public class Employee
{
    public  int id;
    public  String fullName;

    public Employee(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return id + "  Name :  " + fullName;
    }
}
