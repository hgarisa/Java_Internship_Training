package Java_Reflection.Dynamic_Report_Formatter;
import java.util.*;

public class CsvReportFormatter implements ReportFormatter {
    @Override
    public String format(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder("ID,Type,Amount\n");
        for (Transaction t : transactions) {
            sb.append(t.getId()).append(",")
                    .append(t.getType()).append(",")
                    .append(t.getAmount()).append("\n");
        }
        return sb.toString();
    }
}

