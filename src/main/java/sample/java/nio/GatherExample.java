package sample.java.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by kopelevi on 30/09/2015.
 */
public class GatherExample {

    public static void main(String[] args) {
        try (FileOutputStream aFile = new FileOutputStream("c:\\hi2.txt", true);
             FileChannel outputChannel = aFile.getChannel()) {


            //create buffer with capacity of 48 bytes
            ByteBuffer header = ByteBuffer.allocate(128);
            header.put("From: John\n".getBytes());
            header.flip();

            ByteBuffer body = ByteBuffer.allocate(128);
            body.put("YO YO YO".getBytes());
            body.flip();

            ByteBuffer[] bufferArray = {header, body};

            outputChannel.truncate(10);

            long bytesWriten = outputChannel.write(bufferArray); //write from buffer. (read data from buffers to chanel)
            while (bytesWriten!=0){
                bytesWriten = outputChannel.write(bufferArray); //There is no guarantee of how many bytes the write method writes to the FileChannel, hence the while loop
            }
            outputChannel.force(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
