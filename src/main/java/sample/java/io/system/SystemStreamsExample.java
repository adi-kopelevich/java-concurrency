package sample.java.io.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class SystemStreamsExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            System.out.println("File: " + inputStream.getFD().toString());
        } catch (FileNotFoundException e) {
            System.err.println("File doesnt exists - " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to open file - " + filePath);
            e.printStackTrace();
        }
    }
}
