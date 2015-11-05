package sample.java.io.array;

import java.io.*;
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


        int offset = 2;
        int length = 6;
        System.out.println("Read with offset=" + offset + " and length=" + 6);

        int currentChar = 0;
        try (Reader reader = new BufferedReader(new CharArrayReader(charArray, offset, length), 4096)) {
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
