package sample.java.concurrency.producer.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class ProducerConsumerExecutor {

    public static void main(String[] args) {
        BlockingQueue<Integer> tasks = new ArrayBlockingQueue<Integer>(5);

        final int numOfProducers = 30;
        ExecutorService producers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < numOfProducers; i++) {
            producers.execute(new Producer(tasks));
        }
        producers.shutdown();

        final int numOfConsumers = 30;
        ExecutorService consumers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < numOfConsumers; i++) {
            consumers.execute(new Consumer(tasks));
        }
        consumers.shutdown();
    }

}
