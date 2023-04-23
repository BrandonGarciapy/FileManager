/**
 * @Author Brandon Garcia
 * The following class is the main class that will interact with the user
 * this class contains a menu to perform basic operations on a folder
 *
 *
 *
 *
**/

package main.java.com.fileHandlerApp;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
public class App {



    private static String readValue(String message){
        String path = "";
        try {
            Scanner reader = new Scanner(System.in);
            System.out.print(message);
            path = reader.nextLine();
        }catch (Exception e){
            System.out.println("Please insert a valid value: " + e);
        }

        return path;
    }

    private static String showMenu(){
        System.out.println("________________________________________");
        System.out.println("1- Add files to the list");
        System.out.println("2- Remove files from the list ");
        System.out.println("3- Move files");
        System.out.println("4- Copy files");
        System.out.println("5- Delete files");
        System.out.println("6- Exit");
        System.out.println("________________________________________");

        return readValue("Insert an option (example: 1): ");
    }


    public static void main(String[] args){
        String option = "";
        boolean objectCreated = false;
        System.out.println("Welcome file manager you can perform any of the following tasks: ");
        System.out.print("move files, copy Files, delete files");
        System.out.println("Before performing any operation you must specify a source path and a destiny path");
        System.out.println("**ctrl z to exit.**");
        FileHandler fileHandler = null;
        do {
            try {
                fileHandler = new FileHandler(readValue("Insert source path: "), readValue("Insert destiny path: "));
                objectCreated = true;
            } catch (Exception e) {
                System.out.println("Please provide valid parameters: " + e.getMessage());
            }
        }while (!objectCreated);

        // operations

        while(!option.equals("6")){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Working on source: " + fileHandler.getSourcePath() +  " to " + fileHandler.getDestinyPath());
            System.out.println("*****************FILES LIST*************\n");
            fileHandler.showListFiles();
            System.out.println("****************************************");
            option = showMenu();
            switch (option){
                case "1" -> fileHandler.listFiles(readValue("insert a pattern that match with files (example: \".*\\\\.csv$\"): "));
                case "2" -> fileHandler.clearFilesList();
                case "3" -> fileHandler.moveFiles();
                case "4" -> fileHandler.copyFiles();
                case "5" -> fileHandler.deleteFiles();
                case "6" -> System.out.println("Goodbye");
                default -> System.out.println("Invalid option");
                }
            }
    }

}
