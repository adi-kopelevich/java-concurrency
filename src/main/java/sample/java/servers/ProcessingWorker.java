package sample.java.servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ProcessingWorker implements Runnable {

    private final Socket socket;

    public ProcessingWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        processRequest(socket);
    }

    private void processRequest(Socket socket) {
        long time = System.currentTimeMillis();
        try {
            StringBuilder msg = new StringBuilder();
            InputStream inputStream = socket.getInputStream();
            int currentByte = inputStream.read();
            while (currentByte != 255) {
                msg.append((char) currentByte);
                currentByte = inputStream.read();
            }
            System.out.println("Server Received: " + msg.toString());

            String returnMsg = "HTTP/1.1 200 OK\n\n<html><body>" + "Server: " + time + "(" + msg.toString() + ")</body></html>";

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(returnMsg.getBytes());
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
