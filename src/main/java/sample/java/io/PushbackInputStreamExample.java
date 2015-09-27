package sample.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class PushbackInputStreamExample {

    public static void main(String[] args) {
        int pushbackLimit = 8;
        try (FileInputStream fileInputStream = new FileInputStream("c:\\hi.txt");
             PushbackInputStream input = new PushbackInputStream(fileInputStream, pushbackLimit)) {
            byte[] byteArray = new byte[3];
            byteArray[0] = (byte) input.read();
            System.out.println(String.valueOf((char)byteArray[0]));
            byteArray[1] = (byte) input.read();
            System.out.println(String.valueOf((char)byteArray[1]));
            byteArray[2] = (byte) input.read();
            System.out.println(String.valueOf((char)byteArray[2]));
            input.unread(byteArray);
            System.out.println(String.valueOf((char)input.read()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
