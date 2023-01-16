import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MonthlyReport monthlyReport= new MonthlyReport();
        YearReport yearReport = new YearReport();
        CompareReports compareReports = new CompareReports(monthlyReport, yearReport);
        Scanner scanner = new Scanner(System.in);
        int year =2021; //Год за который считывается годовой отчёт
        int amountMonthlyReports=3; //Количество отчетных месяцев по которым есть отчеты

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.loadFile(amountMonthlyReports);
            } else if (command == 2) {
                yearReport.loadFile(year);
            } else if (command == 3) {
                compareReports.checkReadingReports();
            } else if (command == 4) {
                monthlyReport.printMonthlyInfo();
            } else if (command == 5) {
                yearReport.printYearInfo();
            } else if (command == 0) {
                scanner.close();
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
      //System.out.println("0 - Выход"); //Команда работает, но по условию её в меню быть не должно.
    }
}