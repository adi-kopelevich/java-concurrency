package sample.java.servers;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class ClientServerExecutorExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 123;
        String host = InetAddress.getLocalHost().getHostName();

//        SingelthreadedServerExample server = new SingelthreadedServerExample(port);
//        MultithreadedServerExample server = new MultithreadedServerExample(port);
        ThreadPooledServerExample server = new ThreadPooledServerExample(port, Runtime.getRuntime().availableProcessors());
//                ThreadPooledServerExample server = new ThreadPooledServerExample(port, 4);

        ExecutorService serverExecutor = Executors.newSingleThreadExecutor();
        serverExecutor.execute(server);
        serverExecutor.shutdown();

        long clientStartTime = System.currentTimeMillis();
        int numOfClientRequests = 100000;
        Future[] futures = new Future[numOfClientRequests];
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < numOfClientRequests; i++) {
            futures[i] = executorService.submit(new ClientExample(host, port));
        }
        executorService.shutdown();

        for (int i = 0; i < numOfClientRequests; i++) {
            try {
                futures[i].get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long clientEndTime = System.currentTimeMillis();
        System.out.println("Total time for processing " + numOfClientRequests + " client requests = " + (clientEndTime - clientStartTime));

        server.stopServer();
    }
}
