package Generics_Pratice_Questions.Inventory_Management;

import JavaCollection.JavaMap.Parcel;

public abstract class Item
{
    public String name ;
    public double price ;

    public Item(String name , double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


}
