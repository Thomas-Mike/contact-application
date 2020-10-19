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

    public static Contact createContact(){
        System.out.println("Enter persons full name: ");
        Scanner scan = new Scanner(System.in);
        String fullName = scan.nextLine();
        int phoneNumber = getInt();
        Contact userPerson = new Contact(fullName,phoneNumber);
        return userPerson;
    }

    public static int getInt(){
        System.out.println("Please enter a phone number.");
        Scanner scan = new Scanner(System.in);
        int phoneNumber;
        try{
             phoneNumber = scan.nextInt();
        }catch(InputMismatchException inMisMat){
            System.err.println(inMisMat);
            System.err.println("The value you entered was not an integer,please try again.");
            return getInt();
        }
        return phoneNumber;
    }



}
