package oops.Inheritance.QuestionThree;

// Concrete class: Smart TV
class SmartTV extends SmartDevice implements Connectable {
    private int screenSize;

    public SmartTV(String brand, String model, int screenSize) {
        super(brand, model);
        this.screenSize = screenSize;
    }

    @Override
    public void turnOn() {
        System.out.println(brand + " TV is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println(brand + " TV is turning off.");
    }

    @Override
    public void connectToWiFi(String ssid) {
        System.out.println("Connected TV to WiFi: " + ssid);
    }
}
