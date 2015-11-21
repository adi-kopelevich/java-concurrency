package sample.java.concurrency.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class CounterMultiThreadExecutor {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executor.execute(new CounterRunnable(counter));
        executor.execute(new CounterRunnable(counter));
        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Counter value = "+counter.toString());
    }
}
