package Java_Generics.WildCards;

import java.util.*;
public class PayrollMain
{

    public static void main(String[] args) {
        // Reading from ? extends
        List<Manager> managers = Arrays.asList(
                new Manager("David", 8500, 1500),
                new Manager("Eva", 8800, 1800)
        );

        List<Intern> interns = Arrays.asList(
                new Intern("Tom", 1500),
                new Intern("Jerry", 1400)
        );

        System.out.println("--- Reading Payrolls with ? extends ---");
        PayrollReader.printPayroll(managers); //
        PayrollReader.printPayroll(interns);  //

        // Writing into ? super
        List<Employee> payroll = new ArrayList<>();
        PayrollWriter.addManagers(payroll); //

        System.out.println("\n--- Final Payroll List ---");
        PayrollReader.printPayroll(payroll); //  reuse extends to read
    }


}
