package Test;
import main.java.com.fileHandlerApp.FileHandler;


public class FileHandlerTestDrive {

    public static void main(String[] args){
        String sourcePath =  "C:\\Users\\Brandon\\Downloads";
        String destinyPath = "C:\\Users\\Brandon\\Desktop";
        FileHandler fileHandler = new FileHandler(sourcePath, destinyPath);
        fileHandler.setSourcePath(sourcePath);
        fileHandler.listFiles(".*\\.csv$");
        fileHandler.showListFiles();
        fileHandler.copyFiles();
        fileHandler.moveFiles();
        fileHandler.deleteFiles();
        fileHandler.clearFilesList();

    }

}
