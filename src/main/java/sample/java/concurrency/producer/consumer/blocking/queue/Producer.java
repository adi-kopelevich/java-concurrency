    package sample.java.concurrency.producer.consumer.blocking.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Integer val = new Random().nextInt(100);
        try {
//            Thread.sleep(new Random().nextInt(1000));
            queue.put(val);
            System.out.println(Thread.currentThread().getName() + ": produced "+val+", queue_size: " + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
