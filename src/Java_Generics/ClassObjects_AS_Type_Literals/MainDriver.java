package Java_Generics.ClassObjects_AS_Type_Literals;

public class MainDriver
{

    public static void main(String[] args) {
        try {
            // Simulate database query for a Driver
            Driver driver = DatabaseReader.read(Driver.class, "SELECT * FROM drivers WHERE id = 'DRV001'");
            System.out.println("Retrieved Driver: " + driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
