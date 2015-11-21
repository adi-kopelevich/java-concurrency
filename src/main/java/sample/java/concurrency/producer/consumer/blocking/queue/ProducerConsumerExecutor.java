package sample.java.concurrency.producer.consumer.blocking.queue;

import java.util.concurrent.*;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class ProducerConsumerExecutor {

    private static final int NUM_OF_MSGS = 100;

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        BlockingQueue<Integer> tasks = new ArrayBlockingQueue<Integer>(5);

        ExecutorService producers = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < NUM_OF_MSGS; i++) {
            producers.execute(new Producer(tasks));
        }
        producers.shutdown();

        ExecutorService consumers = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < NUM_OF_MSGS; i++) {
            consumers.execute(new Consumer(tasks));
        }
        consumers.shutdown();

        boolean finishedOnTime = consumers.awaitTermination(10, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();

        if (finishedOnTime) {
            System.out.println("Total [ms]: " + String.valueOf(endTime - startTime));
        }
    }

}
