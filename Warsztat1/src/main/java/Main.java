import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskMenager.tasks = TaskMenager.loadDataToTab(TaskMenager.FILE_NAME);
        TaskMenager.printOptions(TaskMenager.OPTIONS);
        TaskMenager.swichActions(scanner);
    }
}

