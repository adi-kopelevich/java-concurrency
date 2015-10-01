package sample.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class AsyncFileChannelComplitionHandlerWriteExample {

    public static void main(String[] args) {

        CompletionHandler completionHandler = new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("write done...");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        };

        Path path = Paths.get("c:\\hi3.txt");
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Hi MAN!!! U kick ass".getBytes());
            buffer.flip();
            fileChannel.write(buffer, 0,buffer,completionHandler);
            buffer.clear();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
