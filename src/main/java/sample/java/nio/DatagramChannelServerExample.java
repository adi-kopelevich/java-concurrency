package sample.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class DatagramChannelServerExample {

    public static void main(String[] args) {

        InetSocketAddress socketAdd = new InetSocketAddress("localhost", 9876);

        try (DatagramChannel udpChannel = DatagramChannel.open()) {
            udpChannel.bind(socketAdd);

            ByteBuffer buffer = ByteBuffer.allocate(48);

            while (true) {
                System.out.println("Waiting on 9876...");
                udpChannel.receive(buffer);
                buffer.flip(); //get ready for read
                System.out.println("Client call received...");

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get()); // read 1 byte at a time
                }
                System.out.println();
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
