
public class ReportsManages {
    MonthlyReport monthlyReport = new MonthlyReport();
    YearReport yearReport = new YearReport();
    public int reportYear=2021; //Год за который считывается годовой отчёт
    public int amountOfMonth=3; //Количество отчетных месяцев по которым есть отчеты

    //Считываем годовой отчёт
    public void loadYearReport()  {
        yearReport.loadFile(reportYear);
        if (yearReport.yearReportWasRead) {
            System.out.println("Годовой отчет успешно считан.");
        }
    }

    //Считываем месячные отчеты
    public void loadMonthlyReports(){
        monthlyReport.loadFile(amountOfMonth);
        if (monthlyReport.monthlyReportWasRead) {
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
        boolean hasDifferences=true; //Флаг сравнения отчетов
        for (int month = 1; month <= amountOfMonth; month++) {
            if(monthlyReport.profitByMonth(month)!=yearReport.profitByMonthInYear(month)){
                hasDifferences=false;
                System.out.println("Обнаружено несоответствие по прибыли годового отчета с отчетом за "+monthName[month-1]+" месяц.");
            }
            if (monthlyReport.wasteByMonth(month)!=yearReport.waistByMonthInYear(month)){
                hasDifferences=false;
                System.out.println("Обнаружено несоответствие по убыткам годового отчета с отчетом за "+monthName[month-1]+" месяц.");
            }
        }
        System.out.println("Результат проверки - "+hasDifferences);
    }
}
