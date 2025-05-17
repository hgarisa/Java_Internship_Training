package Java_Generics.ClassObjects_AS_Type_Literals;

public class DatabaseReader
{

    public static <T> T read(Class<T> theClass, String sql)
            throws InstantiationException, IllegalAccessException {

        // Simulate database read logic
        System.out.println("Executing SQL: " + sql);

        // Simulate mapping result to instance (just a default instance here)
        return theClass.newInstance(); // For Java 9+, use getDeclaredConstructor().newInstance()
    }
}
