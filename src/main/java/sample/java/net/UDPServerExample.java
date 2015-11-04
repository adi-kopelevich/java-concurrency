package sample.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class UDPServerExample {

    public static void main(String[] args) {
        byte[] buffer = new byte[60000];
        try (DatagramSocket datagramSocket = new DatagramSocket(321)) {
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 10);
            datagramSocket.receive(datagramPacket);

            StringBuilder recievedData = new StringBuilder();
            byte[] receivedBytes = datagramPacket.getData();
            System.out.println("datagramPacket.getData(): " + Arrays.toString(receivedBytes));
            for (int i = 0; i < 10; i++) {

                System.out.print("Recived Byte: " + receivedBytes[i]);
                char receivedChar = (char) receivedBytes[i];
                System.out.println(", Recived Char: " + receivedChar);
                recievedData.append(receivedChar);
            }
            System.out.println("Received: " + recievedData);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
