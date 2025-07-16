package Java_Reflection.Dynamic_Report_Formatter;
import java.util.*;

public class PlainTextReportFormatter implements ReportFormatter {
    @Override
    public String format(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder("Transaction Summary:\n");
        for (Transaction t : transactions) {
            sb.append(" - ID: ").append(t.getId())
                    .append(" | Type: ").append(t.getType())
                    .append(" | Amount: $").append(t.getAmount()).append("\n");
        }
        return sb.toString();
    }
}

