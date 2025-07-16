package Java_Reflection.Medical_Patient_Records;

/*
 * Dynamically inspects and logs all fields of the patient using reflection.
 */
import java.lang.reflect.Field;
public class PatientAuditLogger {
    public void log(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("---- Auditing object of type: " + clazz.getSimpleName() + " ----");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                System.err.println("Unable to access field: " + field.getName());
            }
        }
        System.out.println("----------------------------------------------------\n");
    }
}
