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
    private volatile boolean isEnabled = true;
    private ServerSocket serverSocket = null;

    SingelthreadedServerExample(int port) {
        this.port = port;
        initServerSocket();
    }

    private synchronized void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Starting Server...");
        } catch (IOException e) {
            throw new RuntimeException("Failed to init socket server: ", e);
        }
    }

    private void processRequest(Socket socket) {
        long time = System.currentTimeMillis();
        try {
            StringBuilder msg = new StringBuilder();
            InputStream inputStream = socket.getInputStream();
            int currentByte = inputStream.read();
            while (currentByte != 255) {
                msg.append((char) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println("Server Received: " + msg.toString());

            String returnMsg = "HTTP/1.1 200 OK\n\n<html><body>" + "Singlethreaded Server: " + time + "(" + msg.toString() + ")</body></html>";

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(returnMsg.getBytes());
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnabled(boolean enabled) throws IOException {
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
            try {                // blocking - wait for a client request
                Socket socket = serverSocket.accept();
                System.out.println("Server got a request...");
                processRequest(socket);                 // process the client request
                System.out.println("Server finished processing a request...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
