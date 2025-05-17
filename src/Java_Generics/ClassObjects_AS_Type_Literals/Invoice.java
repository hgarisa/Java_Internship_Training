package Java_Generics.ClassObjects_AS_Type_Literals;

public class Invoice
{

    private String invoiceId;
    private double amount;

    public Invoice() {
        this.invoiceId = "INV0001";
        this.amount = 999.99;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Invoice{id='" + invoiceId + "', amount=" + amount + "}";
    }

}
