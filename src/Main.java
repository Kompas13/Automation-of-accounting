import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReportsManages reportsManages = new ReportsManages();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
               reportsManages.loadMonthlyReports();
            } else if (command == 2) {
               reportsManages.loadYearReport();
            } else if (command == 3) {
                reportsManages.checkReadingReports();
            } else if (command == 4) {
                reportsManages.printMonthInfo();
            } else if (command == 5) {
                reportsManages.printYearInfo();
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
        System.out.println("0 - Выход");
    }
}