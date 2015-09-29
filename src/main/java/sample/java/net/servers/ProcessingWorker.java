package sample.java.net.servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ProcessingWorker implements Runnable {

    private final Socket socket;

    public ProcessingWorker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        processRequest(socket);
    }

    private void processRequest(Socket socket) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            StringBuilder msg = new StringBuilder();
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                msg.append((char) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println("Server Received: " + msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
