package JavaCollectionsPraticeQuestions.SecondRedoQ1;

public class Product
{

    public String name;
    public String category;

    public Double price;


    public Product(String name , String category ,  Double price )
    {

        this.name = name;
        this.category = category;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " Product Name is : " + name + ", Category is : " + category +  ", Price is : " + price ;
    }



}
