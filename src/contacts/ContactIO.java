package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
