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
        try (ServerSocket serverSocket = new ServerSocket(123);
             Socket socket = serverSocket.accept(); //blocks on connection to server
             InputStream inputStream = socket.getInputStream()) {

            StringBuilder recievedData = new StringBuilder();
            int receivedByte = inputStream.read(); // blocking
            while (receivedByte != -1) { // -1 stands for end of data
                System.out.print("Recived Byte: "+receivedByte);
                char receivedChar = (char) receivedByte;
                System.out.println(", Recived Char: "+receivedChar);
                recievedData.append(receivedChar);

                receivedByte = inputStream.read();
            }
            System.out.println("Received: " + recievedData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
