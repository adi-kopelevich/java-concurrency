package sample.java.io.array;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class CharArrayExample {
    public static void main(String[] args) {
        String msg = UUID.randomUUID().toString();
        System.out.println("The Message: " + msg);
        char[] charArray = msg.toCharArray();


        try (CharArrayWriter writer = new CharArrayWriter();) {
            writer.write(charArray);
            System.out.println("CharArrayWriter: " + String.valueOf(writer.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int currentChar = 0;
        try (CharArrayReader reader = new CharArrayReader(charArray)) {
            currentChar = reader.read();
            while (currentChar != -1) {
                System.out.println("Read: " + String.valueOf((char) currentChar));
                currentChar = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
