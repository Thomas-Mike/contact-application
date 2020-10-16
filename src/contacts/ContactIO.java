package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactIO {
    public static Path createDirectoryAndFile(String directoryName, String fileName)throws IOException {
        Path directoryPath = Paths.get(directoryName);
        Path dataFilePath = Paths.get(directoryName, fileName);
        // codeup-java-exercises/directoryName/fileName


        //We have to create a directory first before we create the file.

        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath); //creates directory if it doesn't already exist
        }

        if (!Files.exists(dataFilePath)) {
            Files.createFile(dataFilePath);
        }

        return dataFilePath;
    }

    public static void addContact(){
        System.out.println("Enter persons full name name: ");
        Scanner scan = new Scanner(System.in);
        String fullName = scan.nextLine();
        System.out.println("Please enter their phone number");
       int phoneNumber = 0;
        try{
           phoneNumber = scan.nextInt();
       }catch (InputMismatchException ignored){
            addContact(); //Broken code
       }
        Contact userPerson = new Contact(fullName,phoneNumber);
        System.out.println(userPerson.getPhoneNumber());
    }
}
