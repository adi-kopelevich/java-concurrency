package sample.java.io.stream;

import java.io.*;
import java.util.Arrays;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class ReaderWrriterStreamExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        String msg = "Hi,\nthis is my char message...\n";
        char[] msgChars = msg.toCharArray();
        System.out.println("Message: " + msg);
        System.out.println("Message Chars: " + Arrays.toString(msgChars));

        try (Writer writer = new FileWriter(filePath, true)) {
            writer.write(msgChars);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Reader reader = new FileReader(filePath)) {
            int currentChar = reader.read();
            while (currentChar != -1) {
                System.out.println("Read: " + (char) currentChar);
                currentChar = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
