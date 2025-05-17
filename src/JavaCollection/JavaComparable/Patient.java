package JavaCollection.JavaComparable;
import java.util.*;

public class Patient implements Comparable<Patient>
{

    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // equals(): based on unique patientId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient other = (Patient) o;
        return Objects.equals(this.patientId, other.patientId);
    }

    // hashCode(): must be consistent with equals()
    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }

    // compareTo(): sort by age (ascending), then by name
    @Override
    public int compareTo(Patient other) {
        int ageCompare = Integer.compare(this.age, other.age);
        if (ageCompare != 0) {
            return ageCompare;
        }
        return this.name.compareTo(other.name);  // Secondary comparison if ages are equal
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID='" + patientId + '\'' +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }


}
