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
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 123);
             OutputStream outputStream = socket.getOutputStream()) {
            String msgToBeSent = UUID.randomUUID().toString();
            System.out.println("Msg to be sent: " + msgToBeSent);
            byte[] bytesToBeSent = msgToBeSent.getBytes();
            outputStream.write(bytesToBeSent);
            outputStream.flush();
            System.out.println("Sent Bytes: " + Arrays.toString(bytesToBeSent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
