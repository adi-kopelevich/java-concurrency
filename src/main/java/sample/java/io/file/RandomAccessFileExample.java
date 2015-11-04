package sample.java.io.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class RandomAccessFileExample {

    public static void main(String[] args) {
        String filePath = "C:\\hi.txt";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            int byteAt = 6;
            randomAccessFile.seek(byteAt);
            System.out.println("byteAt = " + randomAccessFile.getFilePointer() + " - " + String.valueOf((char) randomAccessFile.read()));
            randomAccessFile.seek(byteAt-1);
            randomAccessFile.write("###".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
