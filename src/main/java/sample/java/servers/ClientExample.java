package sample.java.servers;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ClientExample implements Runnable {

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
                    new OutputStreamWriter(socket.getOutputStream()), 4096);
            outputStreamBufferedWriter.write(msg);
            outputStreamBufferedWriter.write('$');
            outputStreamBufferedWriter.flush();
            System.out.println(Thread.currentThread().getName() + ": Request - " + msg);

            StringBuilder responseMsg = new StringBuilder();
            Reader inputStreamBufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()), 4096);
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
