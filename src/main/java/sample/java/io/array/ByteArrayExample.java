package sample.java.io.array;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class ByteArrayExample {

    public static void main(String[] args) {
        byte[] bytesArray = new byte[16];
        new Random().nextBytes(bytesArray);

        System.out.println("Bytes: " + Arrays.toString(bytesArray));

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            outputStream.write(bytesArray);
            System.out.println("Writer: " + Arrays.toString(outputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytesArray);
        int currentByte = inputStream.read();
        while (currentByte != -1) {
            System.out.println("Read: " + (byte) currentByte);
            currentByte = inputStream.read();
        }

    }
}
