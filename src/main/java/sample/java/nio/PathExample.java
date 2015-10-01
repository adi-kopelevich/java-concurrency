package sample.java.nio;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class PathExample {

    public static void main(String[] args) {

        Path sourcePath = Paths.get("C:\\hi.txt");
        Path destinationPath = Paths.get("C:\\hi3.txt");

        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            System.out.println("destination file already exists");
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }

    }
}
