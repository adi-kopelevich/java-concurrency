package sample.java.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class ServerSocketChannelExample {

    public static void main(String[] args) {
        int port = 9876;
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostName(), port);
            serverSocketChannel.configureBlocking(false); // accept will not block

            serverSocketChannel.bind(socketAddress);
            while (!serverSocketChannel.isOpen()) {
                //wait, or do something else...
            }

            System.out.println("Server is listening on port: " + port);

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept(); // the method may return before a connection is established

                if (socketChannel != null) {

                    System.out.println("Client request accepted...");

                    socketChannel.configureBlocking(false);  // connect/read/write will not block

                    ByteBuffer buffer = ByteBuffer.allocate(16);

                    int bytesRet = socketChannel.read(buffer);  // method may return without having read any data at all. need to pay attention to the returned int
                    System.out.println("bytes read = " + bytesRet);
                    while (bytesRet != -1) {
                        System.out.println("******************************");
                        buffer.flip();

                        while (buffer.hasRemaining()) {
                            System.out.println((char) buffer.get());
                        }
                        buffer.clear();
                        bytesRet = socketChannel.read(buffer);
                    }

                    socketChannel.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
