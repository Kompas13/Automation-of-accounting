import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MonthlyReport {

    public HashMap<Integer, ArrayList<MonthReportRecord>> allMonthReports = new HashMap<>();
    MonthReportRecord monthReportRecord;
    public boolean monthlyReportWasRead = false;

    //Загрузка и чтение файла отчета:
    public void loadFile(int amountMonthlyReports) { //Загрузка информации из файлов отчетов
        String content="";
        for (int month=1; month<=amountMonthlyReports; month++) {
            ArrayList <MonthReportRecord> allDataInReport = new ArrayList<>();
            if (month<10){
                content = readFileContents("resources//m.20210" + month + ".csv");
            } else {
                content = readFileContents("resources//m.2021" + month + ".csv");
            }
            if (Objects.requireNonNull(content).isEmpty()) {
                System.out.println("Проверьте файл");
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
                monthlyReportWasRead = true;
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
        ArrayList <MonthReportRecord> dataInReport;
        dataInReport = allMonthReports.get(month);
        for (MonthReportRecord data : dataInReport) {
            if(!data.isExpense){
                if(data.quantity*data.sumOfOne>profit){
                    profit=data.quantity*data.sumOfOne;
                    productName = data.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: "+ productName+" - "+NumberFormat.getInstance().format(profit));
    }

    //Поиск самой большой траты (4.2)
    public void searchBigWaste(int month){
        int waste=0;
        String wasteName="";
        ArrayList <MonthReportRecord> dataInReport;
        dataInReport = allMonthReports.get(month);
        for (MonthReportRecord data : dataInReport) {
            if(data.isExpense){
                if(data.quantity*data.sumOfOne>waste){
                    waste=data.quantity*data.sumOfOne;
                    wasteName = data.itemName;
                }
            }
        }
        System.out.println("Самая большая трата: "+ wasteName+" - "+NumberFormat.getInstance().format(waste));
    }

    //Прибыль по каждому месяцу:
    public int profitByMonth (int month){
        int profit=0;
        ArrayList <MonthReportRecord> dataInReport;
        dataInReport = allMonthReports.get(month);
        for (MonthReportRecord data : dataInReport) {
            if (!data.isExpense) {
                profit += data.quantity * data.sumOfOne;
            }
        }
        return profit;
    }

    //Траты по каждому месяцу
    public int wasteByMonth (int month){
        int waste=0;
        ArrayList <MonthReportRecord> dataInReport;
        dataInReport = allMonthReports.get(month);
        for (MonthReportRecord data : dataInReport) {
            if (data.isExpense) {
                waste += data.quantity * data.sumOfOne;
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