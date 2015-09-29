package sample.java.net.servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class SingelthreadedServerExample implements Runnable {

    private final int port;
    private boolean isEnabled = true;
    private ServerSocket serverSocket = null;

    SingelthreadedServerExample(int port) {
        this.port = port;
        initServerSocket();
    }

    private void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Starting Server...");
        } catch (IOException e) {
            throw new RuntimeException("Failed to init socket server: ", e);
        }
    }

    private void processRequest(Socket socket) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis();
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            StringBuilder msg = new StringBuilder();
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                msg.append((char) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println("Server Received: " + msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setEnabled(boolean enabled) throws IOException {
        this.isEnabled = enabled;
        if (enabled) {
            System.out.println("Server is going up...");
            initServerSocket();
        } else {
            System.out.println("Server is going down...");
            serverSocket.close();
        }
    }

    @Override
    public void run() {
        while (isEnabled) {
            try (Socket socket = serverSocket.accept()) {                // blocking - wait for a client request
                System.out.println("Server got a request...");
                processRequest(socket);                 // process the client request
                System.out.println("Server finished processing a request...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
