package Java_Generics.Methods;

import java.util.*;
public class GenericUtils
{

    public static <T> T addAndReturn(T element, Collection<T> collection) {
        collection.add(element);
        return element;
    }
}
