public class MonthReportRecord {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthReportRecord(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}