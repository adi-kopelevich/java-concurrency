package sample.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class TCPServerExample {

    public static void main(String[] args) {
        int port = 123;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             InputStream inputStream = socket.getInputStream()) {
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                System.out.println("Received: " + (byte) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println("Done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
