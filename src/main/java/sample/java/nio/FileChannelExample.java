package sample.java.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by kopelevi on 30/09/2015.
 */
public class FileChannelExample {

    public static void main(String[] args) {
        try (FileInputStream aFile = new FileInputStream("c:\\hi.txt");
             FileChannel inChannel = aFile.getChannel()) {

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
