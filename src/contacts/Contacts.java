package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Contacts {
    public static void main(String[] arg) throws IOException {
        String directoryName = "data";
        String fileName = "contact_List.txt";
        Path directoryPath = Paths.get(directoryName);
        Path dataFilePath = Paths.get(directoryName, fileName);
        try {
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            if (!Files.exists(dataFilePath)) {
                Files.createFile(dataFilePath);
            }
        } catch (IOException ex) {
            System.out.println("Cannot create the file.");
            ex.printStackTrace();
        }
        showMainMenu(dataFilePath);
    }

    public static void showMainMenu(Path dataFilePath) throws IOException {
        // ALLOW USER TO SELECT AN OPTION
        Scanner input = new Scanner(System.in);
        System.out.print("1. View contacts.Contacts.\n2. Add a New Contact\n3. Search a Contact by Name\n4. Delete an" +
                " " +
                "Existing Contact\n5. EXIT\nEnter an Option (1, 2, 3, 4, or 5): ");
        int menuOption = 0;
        try {
            menuOption = input.nextInt();
        } catch (InputMismatchException ignored) {
        }

        // LIST OF OPTIONS
        switch (menuOption) {
            case 1:
                ContactIO.viewContacts(dataFilePath);
                break;
            case 2:
                ContactIO.addContact(dataFilePath);
                break;
            case 3:
                System.out.println("Search Contacts");
                break;
            case 4:
                System.out.println("Delete a Contact");
                break;
            case 5:
                System.out.println("EXITING...");
                return;
            default:
                System.out.println("Please Choose Options 1 to 5");
                showMainMenu(dataFilePath);
        }

    }


}
