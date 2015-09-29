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
        try (DatagramSocket socket = new DatagramSocket(80)) {
            int bufferLength = 10;
            byte[] buffer = new byte[bufferLength];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            System.out.println(Thread.currentThread().getName() + ": Received - " + Arrays.toString(packet.getData()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
