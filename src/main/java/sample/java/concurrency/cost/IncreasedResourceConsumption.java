package sample.java.concurrency.cost;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class IncreasedResourceConsumption {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            final int finalI = i;
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println("Started running thread " + finalI);
                        Thread.sleep(10000);
                        System.out.println("Finshide running thread " + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.shutdown();
        }
    }
}
