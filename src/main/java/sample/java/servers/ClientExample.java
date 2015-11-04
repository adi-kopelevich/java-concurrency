package sample.java.servers;

import java.io.*;
import java.net.Socket;
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
        try {
            Socket socket = new Socket(host, port);
            String msg = UUID.randomUUID().toString();

            byte[] dataBytes = msg.getBytes();
            OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream(), 4096);
            outputStream.write(dataBytes);
            outputStream.write(new byte[]{-1});
            outputStream.flush();
            System.out.println(Thread.currentThread().getName() + ": Request - " + msg);

            StringBuilder responseMsg = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(socket.getInputStream(), 4096);
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                responseMsg.append((char) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println(Thread.currentThread().getName() + ": Response - " + responseMsg.toString());
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
