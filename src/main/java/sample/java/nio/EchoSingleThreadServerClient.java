package sample.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class EchoSingleThreadServerClient {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("localhost", 9999);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<String> msgs = new ArrayList<String>(10);
        for (int i = 0; i < 30; i++) {
            msgs.add("MSG" + i);
        }
        for (String msg : msgs) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // sleep randomly, up to 3 sec
                    try {
                        Thread.sleep(new Random().nextInt(10)*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // send message to server
                    try (SocketChannel channel = SocketChannel.open(address)) {
                        System.out.println("Sending message to server...");
                        byte[] message = new String(msg).getBytes();
                        ByteBuffer buffer = ByteBuffer.wrap(message);
                        channel.write(buffer);
                        System.out.println("Sent: " + msg);
                        buffer.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
