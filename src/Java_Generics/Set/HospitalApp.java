package Java_Generics.Set;

import java.util.*;

public class HospitalApp
{
    public static void main(String[] args)
    {

        Set<Patient> patients = new HashSet<>();
        // Adding patients
        patients.add(new Patient("P001", "Alice", 30, "Flu"));
        patients.add(new Patient("P002", "Bob", 45, "Diabetes"));
        patients.add(new Patient("P003", "Charlie", 28, "Fracture"));

        // Trying to add a duplicate patient with same patientId
        patients.add(new Patient("P001", "Alice", 30, "Cold"));  // Same ID, different diagnosis

        System.out.println("--- Patient Records ---");
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getPatientId()
                    + ", Name: " + patient.getName()
                    + ", Age: " + patient.getAge()
                    + ", Diagnosis: " + patient.getDiagnosis());
        }



    }


}
