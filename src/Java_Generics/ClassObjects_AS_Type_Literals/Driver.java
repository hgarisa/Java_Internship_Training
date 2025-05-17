package Java_Generics.ClassObjects_AS_Type_Literals;

public class Driver
{
    private String id;
    private String name;

    public Driver() {
        this.id = "DRV001";
        this.name = "John Doe";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Driver{id='" + id + "', name='" + name + "'}";
    }




}
