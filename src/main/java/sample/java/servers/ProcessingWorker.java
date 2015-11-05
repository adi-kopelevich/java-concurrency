package sample.java.servers;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ProcessingWorker implements Runnable {

    private final Socket socket;

    private static final int BUFFER_SIZE = 4096;

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
            Reader inputStreamBufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8), BUFFER_SIZE);
            int currentChar = inputStreamBufferedReader.read();
            while (currentChar != '$') { // $ - msg delimiter, UUID shouldnt contain '$' character
                msg.append((char) currentChar);
                currentChar = inputStreamBufferedReader.read();
            }
            System.out.println("Server Received: " + msg.toString());

            String returnMsg = "HTTP/1.1 200 OK\n\n<html><body>" + "Server: " + time + "(" + msg.toString() + ")</body></html>";

            Writer outputStreamBufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), BUFFER_SIZE);
            outputStreamBufferedWriter.write(returnMsg);
            outputStreamBufferedWriter.flush();

            inputStreamBufferedReader.close();
            outputStreamBufferedWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
