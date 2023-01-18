
public class ReportsManages {
    MonthlyReport monthlyReport = new MonthlyReport();
    YearReport yearReport = new YearReport();
    public int reportYear=2021; //Год за который считывается годовой отчёт
    public int amountOfMonth=3; //Количество отчетных месяцев по которым есть отчеты

    //Считываем годовой отчёт
    public void loadYearReport()  {
        if (yearReport.yearReportWasRead) {
            System.out.println("Годовой отчет уже был считан.");
        }
        else {
            yearReport.loadFile(reportYear);
            System.out.println("Годовой отчет успешно считан.");
        }
    }

    //Считываем месячные отчеты
    public void loadMonthlyReports(){
        if (monthlyReport.monthlyReportWasRead) {
            System.out.println("Месячные отчеты уже были считаны.");
        }
        else {
            monthlyReport.loadFile(amountOfMonth);
            System.out.println("Месячные отчеты успешно считаны.");
        }
    }

    //Печать информации по месяцам
    public void printMonthInfo(){
        monthlyReport.monthlyInfo(amountOfMonth);
    }

    //Печать информации за год
    public void printYearInfo(){
        yearReport.yearInfo(reportYear);
    }

    //Проверяем были ли считаны отчеты перед сравнением
    public void checkReadingReports(){
        if(!monthlyReport.monthlyReportWasRead){
            System.out.println("Месячный отчет не был считан!");
        }
        else if (!yearReport.yearReportWasRead){
            System.out.println("Годовой отчет не был считан!");
        }
        else checkReports();
    }

    private void checkReports(){
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        boolean hasDifferences=false; //Флаг сравнения отчетов
        for (int month = 1; month <= amountOfMonth; month++) {
            if(monthlyReport.profitByMonth(month)!=yearReport.profitByMonthInYear(month)){
                hasDifferences=true;
                System.out.println("Обнаружено несоответствие по прибыли годового отчета с отчетом за "+monthName[month-1]+" месяц.");
            }
            if (monthlyReport.wasteByMonth(month)!=yearReport.waistByMonthInYear(month)){
                hasDifferences=true;
                System.out.println("Обнаружено несоответствие по убыткам годового отчета с отчетом за "+monthName[month-1]+" месяц.");
            }
        }
        System.out.println("Результат проверки - "+!hasDifferences);
    }
}
