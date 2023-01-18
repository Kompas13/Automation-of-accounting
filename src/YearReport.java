import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Objects;

public class YearReport {
    HashMap<Integer, Integer> profitData = new HashMap<>();
    HashMap<Integer, Integer> wasteData = new HashMap<>();
    public boolean yearReportWasRead = false;

    //Загрузка и чтение файла отчета:
    public void loadFile(int year) {
        String content = readFileContents("resources/y." + year + ".csv");
        if (Objects.requireNonNull(content).isEmpty()) {
            System.out.println("Проверьте файл");
        } else {
            String[] lines = content.split("\r?\n");

            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(",");
                int month = Integer.parseInt(line[0]);
                int amount = Integer.parseInt(line[1]);
                boolean isExpense = Boolean.parseBoolean(line[2]);
                if(isExpense){
                    wasteData.put(month,amount);
                }
                else {
                    profitData.put(month,amount);
                }
            }
            yearReportWasRead = true;
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
    public int profitByMonthInYear (int month){
        return profitData.get(month);
    }

    //Траты по каждому месяцу
    public int waistByMonthInYear(int month){
        return wasteData.get(month);
    }

    //Средний расход за все месяцы (5.2):
    public int searchMiddleWasteInYear() {
        int waste = 0;
        for (Integer data : wasteData.values()) {
            waste+=data;
        }
        return waste/wasteData.size();
    }

    //Средний доход за все месяцы (5.3):
    public int searchMiddleProfitInYear(){
        int profit=0;
        for (Integer data : profitData.values()) {
            profit+=data;
        }
        return profit/profitData.size();
    }

    //Печать годового отчета:
    public void yearInfo(int reportYear) {
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        NumberFormat f = NumberFormat.getInstance();
        if (yearReportWasRead) {
            System.out.println("Информация по отчету за " + reportYear + " год:");
            for (Integer month : profitData.keySet()) {
                System.out.println("Прибыль за " + monthName[month - 1] + " месяц составила - " + f.format(profitByMonthInYear(month)));
            }
            System.out.println("Средний расход за все месяцы " + reportYear + " года составил - " + f.format(searchMiddleWasteInYear()));
            System.out.println("Средний доход за все месяцы " + reportYear + " года составил - " + f.format(searchMiddleProfitInYear()));
        }
        else System.out.println("Пожалуйста, предварительно считайте отчет.");
    }
}