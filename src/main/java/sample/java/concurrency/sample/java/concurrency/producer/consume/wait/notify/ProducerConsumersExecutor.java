package sample.java.concurrency.sample.java.concurrency.producer.consume.wait.notify;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class ProducerConsumersExecutor {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        final int limit = 3;

        final int numOfProducers = 30;
        ExecutorService producers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < numOfProducers; i++) {
            producers.execute(new Producer(list, limit));
        }
        producers.shutdown();

        final int numOfConsumers = 30;
        ExecutorService consumers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < numOfConsumers; i++) {
            consumers.execute(new Consumer(list, limit));
        }
        consumers.shutdown();
    }

}
