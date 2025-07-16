package Java_Reflection.Dynamic_Report_Formatter;
import java.io.Serializable;

public class Transaction implements Serializable {
    private String id;
    private String type;
    private double amount;

    public Transaction(String id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
}
