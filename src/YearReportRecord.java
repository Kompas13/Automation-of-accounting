public class YearReportRecord {
    int month;
    int amount;
    boolean isExpense;

    public YearReportRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}