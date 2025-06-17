package JavaCollectionsPraticeQuestions.Q4Map;

public class Customer
{
    public int number ;
    public String name ;
    public String phoneNo ;

    public Customer(int number , String name , String phoneNo )
    {

        this.number = number ;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Customer Number " + number +  " , " + " Customer Name " + name + " , " + " Phone no " + phoneNo;
    }

}
