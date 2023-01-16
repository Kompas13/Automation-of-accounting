import java.util.HashMap;

public class CompareReports {
    public MonthlyReport monthlyReport;
    public YearReport yearReport;

    public CompareReports(MonthlyReport monthlyReport, YearReport yearReport) {
        this.monthlyReport = monthlyReport;
        this.yearReport = yearReport;
    }

    public void checkReadingReports(){
        if(!monthlyReport.monthlyReportWasRead){
            System.out.println("Месячный отчет не был считан!");
        }
        else if (!yearReport.YearReportWasRead){
            System.out.println("Годовой отчет не был считан!");
        }
        else checkReports();
    }

    private void checkReports(){
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        boolean flag=true; //Флаг сравнения отчетов

        //переупаковываем месяцы в формат HashMap<month, HashMap<isExpense, amount>>
        HashMap<Integer,HashMap<Boolean, Integer>>  reportsByMonth = new HashMap<>();
        for (int i = 1; i <= monthlyReport.amountOfMonth; i++) {
            int profit=0, waste=0;
            for (MonthData data : monthlyReport.allMonthData) {
                if (data.month == i&&data.isExpense){
                    waste+=data.sumOfOne*data.quantity;
                }
                else if (data.month == i){
                    profit+=data.sumOfOne*data.quantity;
                }
            }
            HashMap<Boolean,Integer> financeReports = new HashMap<>();
            financeReports.put(true, waste);
            financeReports.put(false, profit);
            reportsByMonth.put(i, financeReports);
        }

        //переупаковываем год в формат HashMap<month, HashMap<isExpense, amount>>
        HashMap<Integer,HashMap<Boolean, Integer>>  reportsByYear = new HashMap<>();
        for (int i = 1; i <= monthlyReport.amountOfMonth; i++){
            HashMap<Boolean,Integer> financeReports = new HashMap<>();
            for (YearData data : yearReport.allYearData) {
                if(data.month==i) {
                    financeReports.put(data.isExpense, data.amount);
                }
            }
            reportsByYear.put(i, financeReports);

        }

        //Сравниваем HashMap:
        for (Integer month : reportsByMonth.keySet()) {
            HashMap<Boolean, Integer> financeMonthReports = reportsByMonth.get(month);
            HashMap<Boolean, Integer> financeYearReports = reportsByYear.getOrDefault(month,null);
            for (Boolean profitOrWaste : financeMonthReports.keySet()) {
                if ((int)financeMonthReports.get(profitOrWaste)!=(int)financeYearReports.getOrDefault(profitOrWaste,0)){ //приводим типы, так как Integer и Integer не сравниваются (и 2 часа улетели в трубу:))
                    System.out.println("В отчете за "+monthName[month-1]+" обнаружено несоответствие с годовым отчетом");
                    flag=false;
                }
            }
        }
        System.out.println("Результат проверки - "+flag);
    }


}
