package Enums.User_Role;

public class AccessControl
{
    public static void printAccess(User user) {
        System.out.print("User: " + user.getUsername() + " - ");
        switch (user.getRole()) {
            case ADMIN -> System.out.println("Full access granted.");
            case MANAGER -> System.out.println("Moderate access granted.");
            case STAFF -> System.out.println("Limited access granted.");
            case VIEWER -> System.out.println("Read-only access granted.");
        }
    }


}
