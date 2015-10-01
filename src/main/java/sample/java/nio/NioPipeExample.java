package sample.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class NioPipeExample {
    public static void main(String[] args) {

        final Pipe pipe;
        try {
            pipe = Pipe.open();


            ExecutorService sinkExecutor = Executors.newSingleThreadExecutor();
            sinkExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        try {
                            Thread.sleep(1000);


                            Pipe.SinkChannel sinkChannel = pipe.sink();

                            ByteBuffer buffer = ByteBuffer.allocate(48);
                            buffer.put("This is a message...".getBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }
                            System.out.println(Thread.currentThread().getName() + ": sent message to pipe...");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            sinkExecutor.shutdown();

            ExecutorService sourceExecutor = Executors.newSingleThreadExecutor();
            sourceExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        Pipe.SourceChannel sourceChannel = pipe.source();

                        ByteBuffer buffer = ByteBuffer.allocate(48);

                        int bytesRet = sourceChannel.read(buffer);
                        while (bytesRet != -1) {
                            StringBuilder stringBuilder = new StringBuilder();
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                stringBuilder.append((char) buffer.get());
                            }
                            buffer.clear();
                            bytesRet = sourceChannel.read(buffer);
                            System.out.println(Thread.currentThread().getName() + ": received message from pipe:" + stringBuilder.toString());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            sourceExecutor.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
