package sample.java.concurrency.cost;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class IncreasedResourceConsumption {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+": Started");
                        Thread.sleep(new Random().nextInt(10000));
                        System.out.println(Thread.currentThread().getName()+": Finished");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.shutdown();
        }
    }
}
