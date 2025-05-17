package Java_Generics.Classes;

public class GenericFactory <T>
{
    Class<T> theClass = null;

    public GenericFactory(Class<T> theClass) {
        this.theClass = theClass;
    }

    public T createInstance() throws InstantiationException, IllegalAccessException {
        return theClass.newInstance(); // For Java 9+, use getDeclaredConstructor().newInstance()
    }
}
