package sample.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class DatagramChannelClientExample {

    public static void main(String[] args) {

        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            InetSocketAddress socketAdd = new InetSocketAddress("localhost", 9876);
            datagramChannel.bind(null);

            String msg = "This is my UDP NIO message in a bottle";
            ByteBuffer buffer = ByteBuffer.allocate(48);
            buffer.put(msg.getBytes());
            buffer.flip();

            datagramChannel.send(buffer, socketAdd);

//            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
