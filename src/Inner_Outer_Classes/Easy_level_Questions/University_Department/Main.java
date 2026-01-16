package Inner_Outer_Classes.Easy_level_Questions.University_Department;

/*
 Task:
         Create a class University with a static nested class called Department.

        The Department class should have:

        A field departmentName

        A constructor to initialize it

        A method printDepartment() that prints:
        Department: <departmentName>

In the main() method (or a separate class), create and use the Department class like this:

        University.Department dept = new University.Department("Computer Science");
        dept.printDepartment();

*/

public class Main
{
    public static void main(String[] args)
    {

        University.Department mydept = new University.Department("Computer Science");
        mydept.printDepartment();
    }
}
