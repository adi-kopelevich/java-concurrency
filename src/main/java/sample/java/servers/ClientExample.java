package sample.java.servers;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ClientExample implements Runnable {

    private static final int BUFFER_SIZE = 4096;

    private final String host;
    private final int port;

    public ClientExample(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            String msg = UUID.randomUUID().toString();

            Writer outputStreamBufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), BUFFER_SIZE);
            outputStreamBufferedWriter.write(msg);
            outputStreamBufferedWriter.write('$');
            outputStreamBufferedWriter.flush();
            System.out.println(Thread.currentThread().getName() + ": Request - " + msg);

            StringBuilder responseMsg = new StringBuilder();
            Reader inputStreamBufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8), BUFFER_SIZE);
            int currentByte = inputStreamBufferedReader.read();
            while (currentByte != -1) {
                responseMsg.append((char) currentByte);
                currentByte = inputStreamBufferedReader.read();
            }
            System.out.println(Thread.currentThread().getName() + ": Response - " + responseMsg.toString());
            inputStreamBufferedReader.close();
            outputStreamBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
