package Java_Reflection.Dynamic_Report_Formatter;
import java.util.*;

public class JsonReportFormatter implements ReportFormatter {
    @Override
    public String format(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder("[\n");
        for (Transaction t : transactions) {
            sb.append("  {\n")
                    .append("    \"id\": \"").append(t.getId()).append("\",\n")
                    .append("    \"type\": \"").append(t.getType()).append("\",\n")
                    .append("    \"amount\": ").append(t.getAmount()).append("\n")
                    .append("  },\n");
        }
        if (!transactions.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove last comma
        }
        sb.append("\n]");
        return sb.toString();
    }
}
