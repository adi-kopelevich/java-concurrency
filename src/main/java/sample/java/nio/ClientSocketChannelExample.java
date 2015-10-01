package sample.java.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class ClientSocketChannelExample {



    public static void main(String[] args) {

        int port = 9876;

        try (SocketChannel socketChannel = SocketChannel.open()){
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostName(), port);
            socketChannel.configureBlocking(false);

            socketChannel.connect(socketAddress);   // non-blocking, hence the method may return before a connection is established
            while(!socketChannel.finishConnect() ){
                //wait...
            }

            String msg = "This is my TCP NIO message in a bottle";
            ByteBuffer buffer = ByteBuffer.allocate(128);
            buffer.put(msg.getBytes());
            buffer.flip();

            while (buffer.hasRemaining()) { //non-blocking, hence the method may return without having written anything. Therefore you need to call the write() method in a loop
                socketChannel.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
