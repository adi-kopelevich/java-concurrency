package sample.java.net.servers;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ClientServerExecutorExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 123;
        String host = InetAddress.getLocalHost().getHostName();

//        SingelthreadedServerExample server = new SingelthreadedServerExample(port);
        MultithreadedServerExample server = new MultithreadedServerExample(port);
        ExecutorService serverExecutor = Executors.newSingleThreadExecutor();
        serverExecutor.execute(server);
        serverExecutor.shutdown();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new ClientExample(host, port));
        }
        executorService.shutdown();

//        Thread.sleep(2000);
//        server.setEnabled(false);
    }
}
