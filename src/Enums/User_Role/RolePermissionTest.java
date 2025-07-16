package Enums.User_Role;

public class RolePermissionTest
{
    public static void main(String[] args) {
        User user = new User("Diego", Role.MANAGER);
        AccessControl.printAccess(user);
    }
}
