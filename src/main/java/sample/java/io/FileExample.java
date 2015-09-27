package sample.java.io;

import java.io.File;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class FileExample {


    public static void main(String[] args) {
        String filePath = "C:\\tmp\\hi.txt";
        File file = new File(filePath);
        file.exists();
        file.mkdirs();
    }

}
