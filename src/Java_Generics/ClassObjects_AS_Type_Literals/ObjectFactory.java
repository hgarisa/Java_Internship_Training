package Java_Generics.ClassObjects_AS_Type_Literals;

public class ObjectFactory
{

    public static <T> T getInstance(Class<T> theClass) throws Exception {
        return theClass.getDeclaredConstructor().newInstance(); // Modern safe version
    }
}
