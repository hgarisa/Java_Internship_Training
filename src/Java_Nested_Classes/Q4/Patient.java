package Java_Nested_Classes.Q4;

import java.util.*;

// Main class Patient
public class Patient {

    private String patientId;
    private String name;
    private int age;
    private MedicalHistory medicalHistory;
    private EmergencyContact emergencyContact;

    public Patient(String patientId, String name, int age, MedicalHistory history, EmergencyContact contact) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = history;
        this.emergencyContact = contact;
    }

    public void displayPatient() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name + ", Age: " + age);
        medicalHistory.displayMedicalHistory();
        emergencyContact.displayContact();
    }

    // Inner Class: MedicalHistory
    public class MedicalHistory {
        private List<String> illnesses;
        private List<String> surgeries;
        private List<String> allergies;

        public MedicalHistory(List<String> illnesses, List<String> surgeries, List<String> allergies) {
            this.illnesses = illnesses;
            this.surgeries = surgeries;
            this.allergies = allergies;
        }

        public void displayMedicalHistory() {
            System.out.println("Illnesses: " + illnesses);
            System.out.println("Surgeries: " + surgeries);
            System.out.println("Allergies: " + allergies);
        }
    }

    // Static Nested Class: EmergencyContact
    public static class EmergencyContact {
        private String contactName;
        private String phoneNumber;
        private String relation;

        public EmergencyContact(String contactName, String phoneNumber, String relation) {
            this.contactName = contactName;
            this.phoneNumber = phoneNumber;
            this.relation = relation;
        }

        public void displayContact() {
            System.out.println("Emergency Contact: " + contactName + " (" + relation + "), Phone: " + phoneNumber);
        }
    }


    public static void main(String[] args) {
        Patient patient = new Patient("P001", "Emma", 35,
                new Patient("P001", "Emma", 35, null, null)
                        .new MedicalHistory(List.of("Asthma"), List.of("Appendectomy"), List.of("Peanuts")),
                new EmergencyContact("John Doe", "123456789", "Husband"));

        patient.displayPatient();
    }
}

