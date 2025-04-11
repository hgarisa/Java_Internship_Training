package oops.Inheritance.QuestionThree;

// Concrete class: Smart Fridge
class SmartFridge extends SmartDevice implements Connectable {
    private int temperature;

    public SmartFridge(String brand, String model, int temp) {
        super(brand, model);
        this.temperature = temp;
    }

    @Override
    public void turnOn() {
        System.out.println(brand + " Fridge is cooling.");
    }

    @Override
    public void turnOff() {
        System.out.println(brand + " Fridge is off.");
    }

    @Override
    public void connectToWiFi(String ssid) {
        System.out.println("Connected Fridge to WiFi: " + ssid);
    }
}
