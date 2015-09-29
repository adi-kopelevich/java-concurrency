package sample.java.net.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ThreadPooledServerExample implements Runnable {
    private final int port;
    private boolean isEnabled = true;
    private ServerSocket serverSocket = null;
    private final ExecutorService executorService;

    public ThreadPooledServerExample(int port, int threadPoolSize) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(threadPoolSize);
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
                executorService.execute(new ProcessingWorker(socket));                               // process the client request
                System.out.println("Server released request processing...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

}
