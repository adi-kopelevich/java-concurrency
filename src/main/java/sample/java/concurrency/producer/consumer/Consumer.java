package sample.java.concurrency.producer.consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Integer val = null;
        try {
            System.out.println(Thread.currentThread().getName() + ": consumed " + queue.take() + ", queue_size: " + queue.size());
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
