import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MonthlyReport {
    public ArrayList<MonthData> allMonthData = new ArrayList<>();
    public int amountOfMonth;//количество месяцев
    public boolean monthlyReportWasRead = false;

    //Загрузка и чтение файла отчета:
    public void loadFile(int amountMonthlyReports) { //Загрузка информации из файлов отчетов
        amountOfMonth=amountMonthlyReports;
        System.out.println("Месячные отчеты успешно считаны.");
        monthlyReportWasRead = true;
        for (int i=1; i<=amountOfMonth; i++) {
            String content = readFileContents("resources//m.20210" + i + ".csv");
            String[] lines = content.split("\r?\n");
            for (int k = 1; k < lines.length; k++) {
                String[] line = lines[k].split(",");//item_name,is_expense,quantity,sum_of_one
                int month =i;
                String itemName=line[0];
                boolean isExpense = Boolean.parseBoolean(line[1]);
                int quantity = Integer.parseInt(line[2]);
                int sumOfOne = Integer.parseInt(line[3]);
                MonthData monthData = new MonthData(month,itemName, isExpense, quantity, sumOfOne);
                allMonthData.add(monthData);
               // System.out.println(monthData.itemName);
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
    private void searchProfitableProduct(int month){
        int profit=0;
        String productName="";
        for (MonthData data : allMonthData) {
            if(!data.isExpense&& data.month==month){
                if(data.quantity*data.sumOfOne>profit){
                    profit=data.quantity*data.sumOfOne;
                    productName = data.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: "+ productName+" - "+NumberFormat.getInstance().format(profit));
    }

    //Поиск самой большой траты (4.2)
    private void searchBigWaste(int month){
        int waste=0;
        String wasteName="";
        for (MonthData data : allMonthData) {
            if(data.isExpense&& data.month==month){
                if(data.quantity*data.sumOfOne>waste){
                    waste=data.quantity*data.sumOfOne;
                    wasteName = data.itemName;
                }
            }
        }
        System.out.println("Самая большая трата: "+ wasteName+" - "+NumberFormat.getInstance().format(waste));
    }

    //Печать отчета по месяцам:
    public void printMonthlyInfo(){
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