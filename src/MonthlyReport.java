import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MonthlyReport {

    public HashMap<Integer, ArrayList<Object>> allMonthReports = new HashMap<>();
    ArrayList <Object> allDataInReport;
    MonthReportRecord monthReportRecord;
    public boolean monthlyReportWasRead = true;

    //Загрузка и чтение файла отчета:
    public void loadFile(int amountMonthlyReports) { //Загрузка информации из файлов отчетов
        for (int month=1; month<=amountMonthlyReports; month++) {
            allDataInReport = new ArrayList<>();
            String content = readFileContents("resources//m.20210" + month + ".csv");
            if (Objects.requireNonNull(content).isEmpty()) {
                System.out.println("Проверьте файл");
                monthlyReportWasRead = false;
            } else {
                String[] lines = content.split("\r?\n");
                for (int k = 1; k < lines.length; k++) {
                    String[] line = lines[k].split(",");//item_name,is_expense,quantity,sum_of_one
                    String itemName = line[0];
                    boolean isExpense = Boolean.parseBoolean(line[1]);
                    int quantity = Integer.parseInt(line[2]);
                    int sumOfOne = Integer.parseInt(line[3]);
                    monthReportRecord = new MonthReportRecord(itemName, isExpense, quantity, sumOfOne);
                    allDataInReport.add(monthReportRecord);
                }
                allMonthReports.put(month, allDataInReport);
            }
        }
    }

    private String readFileContents (String path){ //Чтение файлов отчетов.
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    //Поиск самого прибыльного товара (4.1)
    public void searchProfitableProduct(int month){
        int profit=0;
        String productName="";
        allDataInReport = allMonthReports.get(month);
        for (Object data : allDataInReport) {
            monthReportRecord = (MonthReportRecord) data;
            if(!monthReportRecord.isExpense){
                if(monthReportRecord.quantity*monthReportRecord.sumOfOne>profit){
                    profit=monthReportRecord.quantity*monthReportRecord.sumOfOne;
                    productName = monthReportRecord.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: "+ productName+" - "+NumberFormat.getInstance().format(profit));
    }

    //Поиск самой большой траты (4.2)
    public void searchBigWaste(int month){
        int waste=0;
        String wasteName="";
        allDataInReport = allMonthReports.get(month);
        for (Object data : allDataInReport) {
            monthReportRecord = (MonthReportRecord) data;
            if (monthReportRecord.isExpense) {
                if (monthReportRecord.quantity * monthReportRecord.sumOfOne > waste) {
                    waste = monthReportRecord.quantity * monthReportRecord.sumOfOne;
                    wasteName = monthReportRecord.itemName;
                }
            }
        }
        System.out.println("Самая большая трата: "+ wasteName+" - "+NumberFormat.getInstance().format(waste));
    }

    //Прибыль по каждому месяцу:
    public int profitByMonth (int month){
        int profit=0;
        allDataInReport = allMonthReports.get(month);
        for (Object data : allDataInReport) {
            monthReportRecord = (MonthReportRecord) data;
            if (!monthReportRecord.isExpense) {
                profit += monthReportRecord.quantity * monthReportRecord.sumOfOne;
            }
        }
        return profit;
    }

    //Траты по каждому месяцу
    public int wasteByMonth (int month){
        int waste=0;
        allDataInReport = allMonthReports.get(month);
        for (Object data : allDataInReport) {
            monthReportRecord = (MonthReportRecord) data;
            if (monthReportRecord.isExpense) {
                waste += monthReportRecord.quantity * monthReportRecord.sumOfOne;
            }
        }
        return waste;
    }

    //Печать отчета по месяцам:
    public void monthlyInfo(int amountOfMonth){
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        if(monthlyReportWasRead){
            for (int i = 1; i <=amountOfMonth; i++) {
                System.out.println("Информация по отчету за "+monthName[i-1]+" месяц:");
                searchProfitableProduct(i);
                searchBigWaste(i);
                System.out.println();
            }
        }
        else System.out.println("Пожалуйста, предварительно считайте отчет.");
    }
}