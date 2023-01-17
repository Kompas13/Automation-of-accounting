import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Objects;

public class YearReport {
    public ArrayList<YearReportRecord> allYearData = new ArrayList<>();
    public boolean yearReportWasRead = true;

    //Загрузка и чтение файла отчета:
    public void loadFile(int year) {
        String content = readFileContents("resources/y." + year + ".csv");
        if (Objects.requireNonNull(content).isEmpty()) {
            System.out.println("Проверьте файл");
            yearReportWasRead = false;
        } else {
            String[] lines = content.split("\r?\n");

            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(",");
                int month = Integer.parseInt(line[0]);
                int amount = Integer.parseInt(line[1]);
                boolean isExpense = Boolean.parseBoolean(line[2]);
                YearReportRecord yearReportRecord = new YearReportRecord(month, amount, isExpense);
                allYearData.add(yearReportRecord);
            }
        }
    }



    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    //Прибыль по каждому месяцу (5.1):
    int profitByMonthInYear (int month){
        int profit=0;
        for (YearReportRecord data : allYearData) {
            if(!data.isExpense&&data.month==month){
                profit+=data.amount;
            }
        }
        return profit;
    }

    //Траты за конкретный месяц
    public int waistByMonthInYear(int month){
        int waist=0;
        for (YearReportRecord data : allYearData) {
            if (data.isExpense&&data.month==month){
                waist = data.amount;
            }
        }
        return waist;
    }

    //Средний расход за все месяцы (5.2):
    public int searchMiddleWasteInYear() {
        int waste = 0;

        for (YearReportRecord data : allYearData) {
            if (data.isExpense){
                waste+= data.amount;
            }
        }
        return waste/(allYearData.size()/2); //расходы за все месяцы/половину длины Arraylist(т.к. половина элементов доходы)
    }

    //Средний доход за все месяцы (5.3):
    public int searchMiddleProfitInYear(){
        int profit=0;
        for (YearReportRecord data : allYearData) {
            if (!data.isExpense){
                profit+= data.amount;
            }
        }
        return profit/(allYearData.size()/2);
    }

    //Печать годового отчета:
    public void yearInfo(int reportYear) {
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        NumberFormat f = NumberFormat.getInstance();
        if (yearReportWasRead) {
            System.out.println("Информация по отчету за " + reportYear + " год:");
            for (int i = 0; i < allYearData.size(); i = i + 2) {
                int month = allYearData.get(i).month;
                System.out.println("Прибыль за " + monthName[month - 1] + " месяц составила - " + f.format(profitByMonthInYear(month)));
            }
            System.out.println("Средний расход за все месяцы " + reportYear + " года составил - " + f.format(searchMiddleWasteInYear()));
            System.out.println("Средний доход за все месяцы " + reportYear + " года составил - " + f.format(searchMiddleProfitInYear()));
        }
        else System.out.println("Пожалуйста, предварительно считайте отчет.");
    }
}