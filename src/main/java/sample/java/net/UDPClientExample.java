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
        int port = 80;
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = UUID.randomUUID().toString().getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), port);
            socket.send(packet);
            System.out.println(Thread.currentThread().getName()+": Sent - "+ Arrays.toString(buffer));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
