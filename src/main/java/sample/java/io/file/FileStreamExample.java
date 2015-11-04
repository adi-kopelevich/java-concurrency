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
            StringBuilder strData = new StringBuilder();
            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[16];
                int numOfBytes = fis.read(buffer);
                while (numOfBytes > 0) {
                    strData.append(new String(buffer, 0, numOfBytes));
//                    System.out.println("numOfBytes= " + numOfBytes + ", Data= " + strData);
                    numOfBytes = fis.read(buffer);
                }
                int srcPos = 0;
                byte[] data = strData.toString().getBytes();
                fos.write(data);
//                byte[] byteBuffer = new byte[16];
//
//                while (srcPos != data.length - 1) {
//                    System.out.println(srcPos);
//                    System.arraycopy(data, srcPos, byteBuffer, 0, byteBuffer.length);
//                    System.out.println(new String(byteBuffer));
//                    fos.write(byteBuffer, 0,  byteBuffer.length);
//                    srcPos = srcPos + byteBuffer.length;
//                }


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
