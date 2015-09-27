package sample.java.io;

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
            randomAccessFile.seek(2);
            System.out.println(String.valueOf((char) randomAccessFile.read()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
