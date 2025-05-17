package Java_Generics.Iterable_Interface;

public class Main
{

    public static void main(String[] args) {
        StudentGroup<Student> group = new StudentGroup<>();

        group.add(new Student("Rick", "S001"));
        group.add(new Student("Harry", "S002"));
        group.add(new Student("George", "S003"));

        // Using enhanced for-loop thanks to Iterable implementation
        for (Student student : group) {
            System.out.println(student);
        }
    }
}
