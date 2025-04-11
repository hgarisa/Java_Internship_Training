package oops.Inheritance.QuestionThree;

public class TestInheritance3
{

    public static void main(String[] args) {
        SmartDevice tv = new SmartTV("Samsung", "QLED", 55);
        SmartDevice fridge = new SmartFridge("LG", "InstaView", 4);

        // Each device behaves differently, even though treated generically
        tv.turnOn();
        ((Connectable) tv).connectToWiFi("HomeNetwork");

        fridge.turnOn();
        ((Connectable) fridge).connectToWiFi("KitchenWiFi");
    }


}
