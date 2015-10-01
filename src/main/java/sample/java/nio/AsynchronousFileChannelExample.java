package sample.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class AsynchronousFileChannelExample {

    private static int readBytesToBuffer(AsynchronousFileChannel fileChannel, ByteBuffer buffer, int position) throws ExecutionException, InterruptedException {
        Future<Integer> future = fileChannel.read(buffer, position);
        while (!future.isDone()) {
            // wait
        }
        return future.get().intValue();
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\hi.txt");
        int pos = 0;
        StringBuilder chunkStringBuilder = new StringBuilder();

        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);) {
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int bytsRead = readBytesToBuffer(fileChannel, buffer, pos);
            while (bytsRead != -1) {
                System.out.println("**** Chunk ****");
                buffer.flip();
                byte[] data = new byte[buffer.limit()];
                buffer.get(data);
                System.out.println(new String(data));
                buffer.clear();
                pos += bytsRead;
                bytsRead = readBytesToBuffer(fileChannel, buffer, pos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
