package sample.java.net;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class UDPClientExample {

    public static void main(String[] args) {
        int bufferLength = 10;
        System.out.println("Buffer length: " + bufferLength);
        String msgToBeSent = UUID.randomUUID().toString();
        System.out.println("Message to be sent: " + msgToBeSent);
        byte[] byteToBeSent = msgToBeSent.getBytes();
        System.out.println("Byte to be sent: " + Arrays.toString(byteToBeSent));
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            DatagramPacket datagramPacket = new DatagramPacket(byteToBeSent, bufferLength, InetAddress.getLocalHost(), 321);
            datagramSocket.send(datagramPacket);
            System.out.println("Message sent.");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
