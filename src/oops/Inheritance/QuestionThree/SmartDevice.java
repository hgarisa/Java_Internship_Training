package oops.Inheritance.QuestionThree;

// Abstract class representing a generic smart device
abstract class SmartDevice {
    protected String brand;
    protected String model;

    public SmartDevice(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    // Must be implemented by concrete subclasses
    public abstract void turnOn();
    public abstract void turnOff();
}
