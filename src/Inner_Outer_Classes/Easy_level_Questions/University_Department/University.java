package Inner_Outer_Classes.Easy_level_Questions.University_Department;

public class University
{

    public static class Department
    {

        private String departmentName;

        public Department(String departmentName)
        {
            this.departmentName = departmentName;
        }
        public void printDepartment()
        {
            System.out.println(" Department : " + departmentName);
        }

    }
}
