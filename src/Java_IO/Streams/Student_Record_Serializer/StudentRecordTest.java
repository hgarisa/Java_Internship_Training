package Java_IO.Streams.Student_Record_Serializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/*

 Scenario
You're building a student information system where:

Admins save student records to a file.

Later, the system loads those records to display or process them.

The objects are stored in a binary format using serialization.


 */

/*
 * Entry point for testing student record serialization/deserialization.
 */
public class StudentRecordTest
{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Step 1: Create student objects
        List<Student> students = Arrays.asList(
                new Student("Harry", 101, 2.9),
                new Student("Natan", 102, 3.5),
                new Student("Drew", 103, 2.7)
        );

        // Step 2: Save to file
        StudentDataManager manager = new StudentDataManager();
        manager.saveStudents(students);

        // Step 3: Load from file
        List<Student> loaded = manager.loadStudents();
        System.out.println("\n Loaded Students from File:");
        loaded.forEach(System.out::println);
    }

}
