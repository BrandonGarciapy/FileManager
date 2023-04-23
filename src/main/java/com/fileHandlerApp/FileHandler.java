/**
 * @author Brandon Garcia
 * @version 1.0
 *
 * This class provides the basic functionality to move files from a specific source path to a
 * destiny path given the pattern matching
 *
 * **/

package main.java.com.fileHandlerApp;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;


public class FileHandler {
    // instance variables
    private Path sourcePath;
    private Path destinyPath;
    private final ArrayList<Path> filesList = new ArrayList<>();


    public FileHandler(String source, String destiny){
        sourcePath = validateDirectoryPath(source);
        destinyPath = validateDirectoryPath(destiny);
    }

    private Path validateDirectoryPath(String path) throws InvalidPathException {
        Path directoryPath = Paths.get(path);

        if (!Files.exists(directoryPath)) throw new InvalidPathException(path, "The path doesn't not exist");
        if(!Files.isDirectory(directoryPath))  throw new InvalidPathException(path, "The path is not a directory.");

        return directoryPath;
    }


    // methods
    public void setSourcePath(String path){
        sourcePath = validateDirectoryPath(path);
    }

    public void setDestinyPath(String path){ destinyPath = validateDirectoryPath(path); }

    public Path getSourcePath(){ return sourcePath; }

    public Path getDestinyPath() { return destinyPath; }


    public void listFiles(String strPattern){
        if(strPattern == null) return;

        try(Stream<Path> stream = Files.walk(sourcePath)){
            stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().matches(strPattern))
                    .forEach(filesList::add);
        }catch (IOException e){
            System.out.println("There was an error: " + e.getMessage());
        }catch (PatternSyntaxException e){

            throw new IllegalArgumentException("The pattern you provide is not correct", e);
        }
    }


    public void showListFiles(){
        if (filesList.size() == 0){
            System.out.println("There are no files");
            return;
        }
        for(Path path: filesList ){
            System.out.println(path.toString());
        }
    }


    private void performFileOperation(Path filePath, String operation){
        /*
        * This method helps us to perform certain operations on each file and
        * separate the logic structure to implement individual operations on in the future
        * */
        Path targetPath = destinyPath.resolve(filePath.getFileName());
        try{
            switch (operation) {
                case "move" -> {
                    System.out.println("Moving file " + filePath + " to " + targetPath);
                    Files.move(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File moved.");
                }
                case "copy" -> {
                    System.out.println("Copying file " + filePath + " to " + targetPath);
                    Files.copy(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File copied");
                }
                case "delete" -> {
                    System.out.println("Deleting file " + filePath);
                    Files.delete(filePath);
                    System.out.println("file deleted");
                }
                default -> System.out.println("Unknown operation type: " + destinyPath);
            }
        }catch (IOException e){
            System.out.println("there was an error while performing the operation " + operation + " " + e.getMessage());
        }
    }

    public void moveFiles(){
        for(Path filePath: filesList){
            performFileOperation(filePath, "move");
        }
    }

    public void copyFiles(){
        for(Path filePath: filesList){
            performFileOperation(filePath, "copy");
        }
    }

    public  void deleteFiles(){
        for(Path filePath: filesList){
            performFileOperation(filePath, "delete");
        }
    }

    public void clearFilesList(){
        filesList.clear();
    }



}


























