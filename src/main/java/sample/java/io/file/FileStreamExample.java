package sample.java.io.file;

import java.io.*;

/**
 * Created by kopelevi on 04/11/2015.
 */
public class FileStreamExample {
    public static void main(String[] args) {
        File sourceFile = new File("c:/hi.txt");
        File destFile = new File("c:/hi_updated.txt");
        if (sourceFile.exists()) {

            try (InputStream fis = new BufferedInputStream(new FileInputStream(sourceFile), 4096);
                 OutputStream fos = new BufferedOutputStream(new FileOutputStream(destFile, true), 4096)) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                // read source files in chunks
                int retByte = fis.read();
                while (retByte > 0) {
                    byteArrayOutputStream.write(retByte);
                    retByte = fis.read();
                }

                // write data to dest files in chunks
                InputStream byteArrayInputStream = new BufferedInputStream(
                        new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), 4096);

                int numOfBytes2 = byteArrayInputStream.read();
                while (numOfBytes2 != -1) {
                    fos.write(numOfBytes2);
                    numOfBytes2 = byteArrayInputStream.read();
                }
                fos.flush();

                byteArrayInputStream.close();
                byteArrayOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesnt exists");
        }

    }

}
