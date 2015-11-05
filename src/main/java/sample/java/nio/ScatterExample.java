package sample.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by kopelevi on 30/09/2015.
 */
public class ScatterExample {

    public static void main(String[] args) {

        try (RandomAccessFile aFile = new RandomAccessFile("c:\\hi2.txt", "rw");
             FileChannel inChannel = aFile.getChannel()) {


            //create buffer with capacity of 48 bytes

            ByteBuffer header = ByteBuffer.allocate(16);
            ByteBuffer body = ByteBuffer.allocate(32);

            ByteBuffer[] bufferArray = {header, body};

            long bytesRead = inChannel.read(bufferArray); //read from chanel write to buffer. (write data into buffers)
            while (bytesRead != -1) {

                System.out.println("--- Header ---");
                header.flip();  //make buffer ready for read
                while (header.hasRemaining()) {
                    System.out.print((char) header.get()); // read 1 byte at a time
                }
                header.clear(); //make buffer ready for writing

                System.out.println("--- Body ---");
                body.flip();  //make buffer ready for read
                while (body.hasRemaining()) {
                    System.out.print((char) body.get()); // read 1 byte at a time
                }
                body.clear(); //make buffer ready for writing
                System.out.println("----------");
                bytesRead = inChannel.read(bufferArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
