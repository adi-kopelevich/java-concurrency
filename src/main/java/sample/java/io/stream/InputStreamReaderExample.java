package sample.java.io.stream;

import java.io.*;
import java.util.Arrays;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class InputStreamReaderExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        String msg = "Hi,\nThis is the text, man...";

        System.out.println("Message: " + msg);
        System.out.println("Message bytes: " + Arrays.toString(msg.toCharArray()));
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)
        ) {
            writer.write(msg.toCharArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int bufferSize = 64 * 1024;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath), bufferSize);
             InputStreamReader reader = new InputStreamReader(inputStream);
        ) {
            int currentChar = reader.read();
            while (currentChar != -1) {
                System.out.println("Read: " + String.valueOf((char) currentChar));
                currentChar = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
