package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactIO {

    public static void addContact(Path dataFilePath) throws IOException {
        String fullName = enterName();
        String phoneNumber = enterPhoneNumber();
        if (!(phoneNumber.length() == 7 || phoneNumber.length() == 10)) {
            addContact(dataFilePath);
        } else {
            String contactString = fullName + ":" + phoneNumber;
            Files.write(dataFilePath, Collections.singletonList(contactString), StandardOpenOption.APPEND);
            System.out.println("\nWould you like to add another contact? [Y/N]");
            String response = goAgain();
            if (response.equalsIgnoreCase("y")) {
                addContact(dataFilePath);
            } else if (response.equalsIgnoreCase("n")) {
                Contacts.showMainMenu(dataFilePath);
            } else {
                goAgain();
            }
        }
    }

    private static String goAgain() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void viewContacts(Path dataFilePath) throws IOException {
        System.out.print("Name                     | Phone Number\n---------------------------------------------");
        List<String> fileContents = Files.readAllLines(dataFilePath);
        System.out.println();
        for (int i = 0; i < fileContents.size(); i++) {
            String name = fileContents.get(i).substring(0, fileContents.get(i).indexOf(':'));
            String phone = fileContents.get(i).substring((fileContents.get(i).indexOf(':')) + 1);
            String formatPhone;
            if (phone.length() == 7) {
                formatPhone = fileContents.get(i).substring(fileContents.get(i).indexOf(':') + 1,
                        fileContents.get(i).indexOf(':') + 4) + "-" + fileContents.get(i).substring(fileContents.get(i).indexOf(':') + 4);
            } else {
                formatPhone = "(" + fileContents.get(i).substring(fileContents.get(i).indexOf(':') + 1,
                        fileContents.get(i).indexOf(':') + 4) + ")" + fileContents.get(i).substring(fileContents.get(i).indexOf(':') + 4,
                        fileContents.get(i).indexOf(':') + 7) + "-" + fileContents.get(i).substring(fileContents.get(i).indexOf(':') + 7);
            }
            System.out.printf("%-25s| %s%n", name, formatPhone);
        }
        Contacts.showMainMenu(dataFilePath);
    }

    public static String enterName() {
        Scanner contactInput = new Scanner(System.in);
        System.out.print("Enter Contact Name: ");
        return contactInput.nextLine().trim();
    }

    public static String enterPhoneNumber() {
        Scanner phoneInput = new Scanner(System.in);
        System.out.print("Enter the Contact's Phone Number: ");
        String phone = phoneInput.nextLine();
        return phone;
    }

}
