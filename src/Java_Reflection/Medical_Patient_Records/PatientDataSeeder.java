package Java_Reflection.Medical_Patient_Records;
import java.io.*;
import java.util.Arrays;
import java.util.List;
/*
 * Utility class to generate test data.
 */

/*
 Scenario:
You work for a hospital's IT department.
Each day, a list of serialized Patient records is saved to disk (e.g., patients.ser).

 You are tasked with building an auditing tool that:

Dynamically inspects and logs the contents of each patient's object (regardless of changes to the class).

Uses Java Reflection to print all fields of each Patient object.

Uses ObjectInputStream to deserialize the records.

This tool ensures that no sensitive fields are omitted during audits
and works even when new fields are added later to the Patient class.


* */

public class PatientDataSeeder {
    public static void main(String[] args) {
        List<Patient> patients = Arrays.asList(
                new Patient("Ollie", 30, "Flu", "Dr.Visser", true),
                new Patient("Lionel", 45, "Migraine", "Dr.Francis", false)
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("patients.ser"))) {
            oos.writeObject(patients);
            System.out.println("Patient data written to patients.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

