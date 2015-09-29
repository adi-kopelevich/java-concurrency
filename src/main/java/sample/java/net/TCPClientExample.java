package sample.java.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class TCPClientExample {
    public static void main(String[] args) {
        int port = 123;
        try (Socket socket = new Socket(InetAddress.getLocalHost(), port);
             OutputStream outputStream = socket.getOutputStream()) {
            String data = UUID.randomUUID().toString();
            byte[] buffer = data.getBytes();
            outputStream.write(buffer);
            outputStream.flush();
            System.out.println("Sent: " + Arrays.toString(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
