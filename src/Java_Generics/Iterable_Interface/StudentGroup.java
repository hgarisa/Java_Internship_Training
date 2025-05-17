package Java_Generics.Iterable_Interface;

import java.util.*;

public class StudentGroup <T> implements Iterable<T>
{

    private List<T> students = new ArrayList<>();

    public void add(T student) {
        students.add(student);
    }

    @Override
    public Iterator<T> iterator() {
        return new StudentGroupIterator<>(students);
    }


}
