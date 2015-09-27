package sample.java.io.stream;

import java.io.*;
import java.util.Arrays;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class InputOutputStreamExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        String msg = "Hi,\nThis is the text, man...";
        byte[] msgBytes = msg.getBytes();

        System.out.println("Message: " + msg);
        System.out.println("Message bytes: " + Arrays.toString(msgBytes));
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            outputStream.write(msgBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int bufferSize = 64 * 1024;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath), bufferSize)) {
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                System.out.println("Read: " + String.valueOf((byte) currentByte));
                currentByte = inputStream.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
