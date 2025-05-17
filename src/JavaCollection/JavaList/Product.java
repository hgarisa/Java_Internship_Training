package JavaCollection.JavaList;

import java.util.*;
public class Product
{
    public String name;
    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " for " + price ;
    }

}
