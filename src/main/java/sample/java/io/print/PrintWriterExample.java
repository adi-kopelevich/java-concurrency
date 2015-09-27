package sample.java.io.print;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class PrintWriterExample {
    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        try (FileWriter writer = new FileWriter(filePath, true);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(true);
            printWriter.println((int) 123);
            printWriter.println((float) 123.456);
            printWriter.printf(Locale.UK, "the %s jumped over the %s, %d times\n", "cow", "moon", 2);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
