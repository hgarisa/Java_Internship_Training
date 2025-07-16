package Java_IO.Files.Expense_Tracker;

public class ExpenseEntry {
    private String date; // Format: yyyy-MM-dd
    private String category;
    private double amount;

    public ExpenseEntry(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}
