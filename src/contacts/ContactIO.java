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
            String formatPhone = formatPhoneNumber(phone, fileContents);
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
                String name = contact.substring(0, contact.indexOf(':'));
                String phone = contact.substring((contact.indexOf(':')) + 1);
                String formatPhone = formatPhoneNumber(phone, fileContents);
                System.out.printf("%-25s| %s%n", name, formatPhone);
            }
        }
        Contacts.showMainMenu(dataFilePath);
    }

    public static void deleteContact(Path dataFilePath) throws IOException{
        System.out.println("Enter the contact you want to delete.");
        Scanner input = new Scanner(System.in);
        String searchParam = input.nextLine();
        List<String> fileContents = Files.readAllLines(dataFilePath);
        List<String> modifiedList = new ArrayList<>();
        for(String contact : fileContents){
            if (!contact.contains(searchParam)){
                modifiedList.add(contact);
            }
        }
        Files.write(dataFilePath, modifiedList);
        Contacts.showMainMenu(dataFilePath);
    }

    public static String formatPhoneNumber(String phoneNumber, List<String> fileContents) {
        String formatPhone;
        if (phoneNumber.length() == 7) {
            formatPhone = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3);
        } else {
            formatPhone =
                    "(" + phoneNumber.substring(0, 3) + ")" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
        }
        return formatPhone;
    }


}
