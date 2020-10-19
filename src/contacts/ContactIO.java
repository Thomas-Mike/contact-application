package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactIO {

    public static void addContact(Path dataFilePath) throws IOException {
        String fullName = enterName();
        Integer phoneValue = getInt();

        String phoneNumber = phoneValue.toString();

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


    public static void searchContacts(Path dataFilePath) throws IOException {
        System.out.println("Enter Contact to Search");
        Scanner input = new Scanner(System.in);
        String searchParam = input.nextLine();
        List<String> fileContents = Files.readAllLines(dataFilePath);
        for (String contact : fileContents) {
            if (contact.contains(searchParam)){
                System.out.println(contact);
            }
        }
        Contacts.showMainMenu(dataFilePath);
    }

}
