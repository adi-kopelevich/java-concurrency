package sample.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class AsyncFileChannelComplitonHandlerExample {

    public static void main(String[] args) throws IOException {

        CompletionHandler<Integer, ByteBuffer> completionHandler = new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                attachment.flip();
                byte[] dataChunk = new byte[result];
                attachment.get(dataChunk);
                System.out.println("**** Data Chunk **** ");
                System.out.println("Data: " + new String(dataChunk));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Failed...");
            }
        };

        Path path = Paths.get("C:\\hi.txt");
        AsynchronousFileChannel asynFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long pos = 0;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                asynFileChannel.read(buffer, pos, buffer, completionHandler);
            }
        });




    }
}
