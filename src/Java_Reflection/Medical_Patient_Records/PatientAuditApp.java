package Java_Reflection.Medical_Patient_Records;
import java.io.*;
import java.util.List;

/*
 * Main auditing app to read and inspect patient records via reflection.
 */
public class PatientAuditApp {
    public static void main(String[] args) {
        String filePath = "patients.ser";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Patient> patients = (List<Patient>) ois.readObject();

            PatientAuditLogger logger = new PatientAuditLogger();
            for (Patient patient : patients) {
                logger.log(patient);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during audit: " + e.getMessage());
        }
    }
}

