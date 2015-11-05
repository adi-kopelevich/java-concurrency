package sample.java.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class AsynchronousFileChannelExample {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String filePathStr = "c:\\hi3.txt";
        Path filePath = Paths.get(filePathStr);
        if (Files.exists(filePath)) {
            try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate(16);
                buffer.clear();

                // first cycle of read chunk from file to buffer
                Future<Integer> future = asynchronousFileChannel.read(buffer, 0);
                while (!future.isDone()) {
                    System.out.println("Waiting...");
                }
                int numberOfBytes = future.get().intValue();

                // loop for reading from buffer, prepare for write and write again
                int pos = 0;
                while (numberOfBytes > 0) {
                    // read from buffer
                    buffer.flip();
                    System.out.println("*** Chunk ***");
                    // read while has remaining
                    while (buffer.hasRemaining()) {
                        stringBuilder.append((char) buffer.get());
                    }
                    // prepare buffer for next write to it
                    buffer.clear();

                    //
                    System.out.println(stringBuilder.toString());

                    // re-cycle of read chunk from file to buffer
                    pos += numberOfBytes;
                    future = asynchronousFileChannel.read(buffer, pos);
                    while (!future.isDone()) {
                        System.out.println("Waiting...");
                    }
                    numberOfBytes = future.get().intValue();
                }
                // print entire data
                System.out.println(stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File dosent exists... " + filePathStr);
        }
    }
}
