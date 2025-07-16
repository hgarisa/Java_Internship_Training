package Java_IO.Streams.Student_Record_Serializer;
import java.io.Serializable;

/*
 * Represents a student with basic information.
 * Implements Serializable to support file-based object storage.
 */
public class Student implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    public String toString() {
        return id + " - " + name + " - GPA: " + gpa;
    }


}
