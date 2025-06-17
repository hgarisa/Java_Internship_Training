package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q2;

import java.util.Map;

public class EmployeeDirectory
{
    public static void main(String[] args)
    {


        // Map.of()
        Map<String , Employee> immutableMap = Map.of(
                "EMP001" , new Employee(12 , "Nathan"),
             "EMP002" , new Employee(13 , "Chris") ,
        "EMP003" , new Employee(14 , "Connor")
        );

        immutableMap.forEach((code , Employee) -> System.out.println(" Code : " + code + " => Employee  " + Employee));


        System.out.println("------------------------------------------------------------------------------------------------------------");
        //Map.ofEntries()

        Map<String , Employee> employeeMap2 = Map.ofEntries(

                Map.entry("EMP001" , new Employee(1 , "PK Humble")),
                Map.entry("EMP002" , new Employee(2 , "Brendon")),
                Map.entry("EMP003" , new Employee(3 , "Morgan Elvis"))

        );
        employeeMap2.forEach((code , employee) -> System.out.println( "Code : " + code + " , ID : " +  employee));


    }
}
