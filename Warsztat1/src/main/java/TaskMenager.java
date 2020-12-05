import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

public class TaskMenager {
    static String[][] tasks;
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {
           }
    public static void printOptions(String[] tab) {

        System.out.println(ConsoleColors.BLUE+"Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }

    }
    public static String[][] loadDataToTab(String fileName){
        Path direction = Paths.get(fileName);

        if (!Files.exists(direction)) {
            System.out.println("No File");
            System.exit(0);
        }


        String[][] tab = null;

        try {
            List<String> strings = Files.readAllLines(direction);

            tab = new String[((List<?>) strings).size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }
    public static void swichActions (Scanner scanner){
        String exit= "exit";

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "add": {
                    addToTable(scanner);
                    break;
                }
                case "remove": {
                   remove(scanner);
                    break;
                }
                case "list": {
                    printTab(tasks);
                    break;
                }
                case "exit": {
                    saveTabToFile(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED+"Bye!");
                    System.exit(0);
                    break;

                }
                default:
                    System.out.println("Please select a correct option.");
            }
            printOptions(OPTIONS);

        }
    }
    public static void addToTable (Scanner scanner){
            try {
                System.out.println(Arrays.deepToString(tasks));

                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length-1]=new String[3];
                System.out.println("Please add task description :");
                tasks[tasks.length -1][0] = scanner.nextLine();
                System.out.println("Please add task due date :");
                tasks[tasks.length -1][1] = scanner.nextLine();
                System.out.println("Is your task important (Type true/false) :");
                tasks[tasks.length -1][2] = scanner.nextLine();
                System.out.println("Done!");


            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
    }
    public static void printTab(String[][] tab) {

        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " :");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public static void remove (Scanner scanner){
        try{
        System.out.println("Type number witch u want delete :");
        int a = scanner.nextInt();

        tasks = ArrayUtils.remove(tasks, a);
        System.out.println("Done");}
        catch (InputMismatchException e){
            System.out.println(ConsoleColors.RED+"Please type Number!"+ConsoleColors.RESET);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(ConsoleColors.RED+"Wrong number!"+ ConsoleColors.RESET);
        }

    }
    public static void saveTabToFile(String fileName, String[][] tab) {

        String[] lines = new String[tasks.length];

        for (int i = 0; i < tab.length; i++) {

            lines[i] = String.join(",", tab[i]);
        }
        try {
            Path direction = Paths.get(FILE_NAME);
            Files.write(direction , Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

}
