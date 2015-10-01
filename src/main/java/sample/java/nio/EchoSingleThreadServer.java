package sample.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class EchoSingleThreadServer {
    private final Selector selector;
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 9999;
    private static final int BUFFER_SIZE = 128;

    public EchoSingleThreadServer() throws IOException {
        selector = initSelector();
        loop();
    }

    private Selector initSelector() throws IOException {
        // open selector
        Selector socketSelector = Selector.open();
        // open server channel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //configure as non-blocking
        serverChannel.configureBlocking(false);
        // create address obj
        InetSocketAddress add = new InetSocketAddress(LOCALHOST, PORT);
        // bind server to address
        serverChannel.socket().bind(add);
        System.out.println("Starting echo server on port " + PORT);

        //register server channel for non-blocking accept operation
        serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
        return socketSelector;
    }

    private void loop() {
        while (true) {
            try {
                System.out.println("Waiting for select (blocking)...");
                int noOfKeys = selector.select();
                System.out.println("Number of available selected ops keys: " + noOfKeys);
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    // get next available op key to handle
                    SelectionKey key = selectedKeys.next();
                    // remove key from the iteration
                    selectedKeys.remove();
                    // Check what event is available and deal with it
                    if (key.isAcceptable()) {
                        handleServerAcceptOp(key);
                    } else if (key.isReadable()) {
                        handleSocketReadOp(key);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Selector loop was interrupted...", e);
            }
        }
    }

    private void handleServerAcceptOp(SelectionKey key) throws IOException {
        // get server channel from acept op key
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        // establish connection socket with client
        SocketChannel socketChannel = serverSocketChannel.accept();
        // configure socket channel ops as non-blocking (read/write)
        socketChannel.configureBlocking(false);
        // register socket's read op to the selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connection established...");
    }

    private void handleSocketReadOp(SelectionKey key) throws IOException {
        // get socket channel from read op key
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // read data from channel
        try {
            ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            int numRead = socketChannel.read(readBuffer);
            if (numRead != -1) {
                // flip buffer in order to read after write is done
                readBuffer.flip();
                // write to console the msg received from client
                System.out.println("Read: " + new String(readBuffer.array()).trim());
                readBuffer.clear();
            } else {                    // end of read...
                // close socket channel
                socketChannel.close();
                // cancel related ops in selector for that socket channel
                key.cancel();
            }
        } catch (IOException e) {
            // close socket channel
            socketChannel.close();
            // cancel related ops in selector for that socket channel
            key.cancel();
            throw new RuntimeException("failed to handle socket read op", e);
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoSingleThreadServer();
    }
}
