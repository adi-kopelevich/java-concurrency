package sample.java.net.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class MultithreadedServerExample implements Runnable {
    private final int port;
    private boolean isEnabled = true;
    private ServerSocket serverSocket = null;

    MultithreadedServerExample(int port) {
        this.port = port;
        initServerSocket();
    }

    private void initServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Starting Server...");
        } catch (IOException e) {
            throw new RuntimeException("Failed to init socket server: ", e);
        }
    }

    public synchronized void setEnabled(boolean enabled) throws IOException {
        this.isEnabled = enabled;
        if (enabled) {
            System.out.println("Server is going up...");
            initServerSocket();
        } else {
            System.out.println("Server is going down...");
            serverSocket.close();
        }

    }

    @Override
    public void run() {
        while (isEnabled) {
            try {                // blocking - wait for a client request
                Socket socket = serverSocket.accept();
                System.out.println("Server got a request...");
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new ProcessingWorker(socket));
                executorService.shutdown();                // process the client request
                System.out.println("Server finished processing a request...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
