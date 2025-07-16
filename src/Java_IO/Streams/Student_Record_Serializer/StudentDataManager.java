package Java_IO.Streams.Student_Record_Serializer;

import java.io.*;
import java.util.List;

/*
 * Handles writing and reading student records from disk
 * using ObjectOutputStream and ObjectInputStream.
 *
 *
  */
public class StudentDataManager
{
    private final String filePath = "students.ser";

    // Serialize and save the list of students to file
    public void saveStudents(List<Student> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(students);
        }
        System.out.println(" Student records serialized to: " + filePath);
    }

    // Deserialize and return the list of students from file
    public List<Student> loadStudents() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Student>) ois.readObject();
        }
    }

}
