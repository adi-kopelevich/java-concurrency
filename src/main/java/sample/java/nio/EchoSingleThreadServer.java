package sample.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 01/10/2015.
 */
public class EchoSingleThreadServer implements Runnable {
    private volatile boolean stop = false;
    private final Selector selector;
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 9999;
    private static final int BUFFER_SIZE = 128;

    public EchoSingleThreadServer() {
        selector = initSelectorWithServerChannel();
    }

    private Selector initSelectorWithServerChannel() {
        try {
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
        } catch (IOException e) {
            throw new RuntimeException("Failed to init selector with server socket channel", e);
        }
    }

    public void stop() {
        this.stop = true;
        System.out.println("Server was stopped...");
    }

    public void run() {
        while (!stop) {
            System.out.println("Waiting for select (blocking)...");
            int noOfKeys = 0;
            try {
                noOfKeys = selector.select();
            } catch (IOException e) {
                System.out.println("Failed to execute select on selector");
                e.printStackTrace();
            } catch (ClosedSelectorException e) {
                System.out.println("Selector was closed, break loop...");
                break;
            }
            if (noOfKeys > 0) {  // select was executed successfully
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
            }
        }
    }

    private void handleServerAcceptOp(SelectionKey key) {
        SocketChannel socketChannel = null;
        // get server channel from acept op key
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        try {
            // establish connection socket with client
            socketChannel = serverSocketChannel.accept();
            // configure socket channel ops as non-blocking (read/write)
            socketChannel.configureBlocking(false);
            // register socket's read op to the selector
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Client connection established...");
        } catch (IOException e) {
            System.out.println("Failed to handle server accept op");
            e.printStackTrace();
            closeSocketChannel(socketChannel);
        }
    }

    private void handleSocketReadOp(SelectionKey key) {
        // get socket channel from read op key
        try (SocketChannel socketChannel = (SocketChannel) key.channel()) {
            // read data from channel
            ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            int numRead = socketChannel.read(readBuffer);
            if (numRead != -1) {
                // flip buffer in order to read after write is done
                readBuffer.flip();
                // write to console the msg received from client
                System.out.println("Read: " + new String(readBuffer.array()).trim());
                readBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("Failed to handle socket read op. cause: " + e);
        }
    }

    private void closeSocketChannel(SocketChannel socketChannel) {
        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (IOException e1) {
                System.out.println("Failed to close socket channel");
                e1.printStackTrace();
            }
        }
    }

    private void tearDown() {
        stop();
        try {
            System.out.println("Going to stop server...");
            if (selector != null) {
                selector.close();
            }
            System.out.println("Server is down.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to close selector", e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EchoSingleThreadServer server = new EchoSingleThreadServer();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(server);
        Thread.sleep(10000);
        server.stop();
        Thread.sleep(5000);
        server.tearDown();
        executorService.shutdown();
    }


}
