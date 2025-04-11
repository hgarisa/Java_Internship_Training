package static_Concepts;

public class AppConfig
{

    // Static variable: Shared across all instances of the class
    private static String appName;
    private static String version;

    // Static block: Executes once when the class is loaded (before main or any method call)
    static {
        System.out.println("Static block is initializing configuration...");
        appName = "Inventory Manager";
        version = "v2.3.1";
    }

    // Static method: Can be called without creating an object
    public static void displayConfig() {
        System.out.println("Application Name: " + appName);
        System.out.println("Version: " + version);
    }

    // Non-static method (for comparison)
    public void greetUser(String user) {
        System.out.println("Welcome " + user + " to " + appName + " " + version);
    }

    public static void main(String[] args) {
        // Static block runs once when the class is loaded into memory

        // Call static method directly using class name (no object needed)
        AppConfig.displayConfig();

        // Call non-static method via object
        AppConfig config = new AppConfig();
        config.greetUser("Alice");
    }


}
