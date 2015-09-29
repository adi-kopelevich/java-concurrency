package sample.java.net.servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ClientExample implements Runnable {

    private final String host;
    private final int port;

    public ClientExample(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()) {
            Thread.sleep(new Random().nextInt(3) * 1000);
            String msg = UUID.randomUUID().toString();
            byte[] dataBytes = msg.getBytes();
            outputStream.write(dataBytes);
            outputStream.flush();
            System.out.println(Thread.currentThread().getName() + ": Sent - " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
