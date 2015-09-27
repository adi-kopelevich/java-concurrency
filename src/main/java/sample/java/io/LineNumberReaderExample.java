package sample.java.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class LineNumberReaderExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";


        try (LineNumberReader reader = new LineNumberReader(new FileReader(filePath))) {
            System.out.println("This is the first line: " + reader.readLine());
            int currentChar = reader.read();
            while (currentChar != -1) {
                System.out.println("Current Char: " + (char) currentChar + ", Current Line: " + reader.getLineNumber());
                currentChar = reader.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
