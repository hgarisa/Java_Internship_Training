package Java_Generics.Classes;

public class Main
{

    public static void main(String[] args) {
        try {
            // Create a factory for UserAccount
            GenericFactory<UserAccount> userFactory = new GenericFactory<>(UserAccount.class);

            // Create an instance using the factory
            UserAccount user = userFactory.createInstance();

            // Use the instance
            user.displayInfo();  // Output: User: guest | Role: viewer

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
