package Java_Generics.Classes;

public class UserAccount
{

    private String username;
    private String role;

    public UserAccount() {
        this.username = "guest";
        this.role = "viewer";
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void displayInfo() {
        System.out.println("User: " + username + " | Role: " + role);
    }
}
