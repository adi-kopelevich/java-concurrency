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

            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(destFile, true)) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                // read source files in chunks
                byte[] buffer = new byte[16];
                int numOfBytes = fis.read(buffer);
                while (numOfBytes > 0) {
                    byteArrayOutputStream.write(buffer, 0, numOfBytes);
                    numOfBytes = fis.read(buffer);
                }

                // write data to dest files in chunks
                byte[] byteBuffer = new byte[16];
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

                int numOfBytes2 = byteArrayInputStream.read(byteBuffer, 0, byteBuffer.length);
                while (numOfBytes2 != -1) {
                    fos.write(new String(byteBuffer, 0, numOfBytes2).getBytes());
                    numOfBytes2 = byteArrayInputStream.read(byteBuffer, 0, byteBuffer.length);
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
