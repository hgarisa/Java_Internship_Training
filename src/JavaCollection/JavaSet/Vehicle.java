package JavaCollection.JavaSet;

public class Vehicle
{
    private String registrationNumber;
    private String model;

    public Vehicle(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    // getters
    public String getRegistrationNumber()  { return registrationNumber; }
    public String getModel() { return model; }

    // Uniqueness based on registrationNumber

    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof Vehicle))
        {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return this.registrationNumber.equals(vehicle.registrationNumber);
    }

    public int hashCode()
    {
        return this.registrationNumber.hashCode();
    }

    public String toString() {
        return "Vehicle {" + "regNumber='" + registrationNumber + '\'' + ", model='" + model + '\'' + '}';
    }



}
