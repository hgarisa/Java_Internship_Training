package Java_Generics.Iterable_Interface;

import java.util.*;

public class StudentGroupIterator <T> implements Iterator<T>
{

    private int index = 0;
    private List<T> list;

    public StudentGroupIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported.");
    }


}
