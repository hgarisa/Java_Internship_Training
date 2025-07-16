package Java_Reflection.Medical_Patient_Records;

import java.io.Serializable;

public class Patient implements Serializable {
    private String name;
    private int age;
    private String diagnosis;
    private String doctorAssigned;
    private boolean admitted;

    public Patient(String name, int age, String diagnosis, String doctorAssigned, boolean admitted) {
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.doctorAssigned = doctorAssigned;
        this.admitted = admitted;
    }


}

