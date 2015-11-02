package sample.java.concurrency.producer.consumer.blocking.queue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by kopelevi on 26/10/2015.
 */
public class NonBockingConsumerProducer {

    private static final int NUM_OF_MSGS = 100;

    public static class Producer implements Runnable {

        private final BlockingQueue<String> blockingQueue;
        private final String msg;

        public Producer(BlockingQueue<String> blockingQueue, String msg) {
            this.blockingQueue = blockingQueue;
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                int retries = 0;
                while (!blockingQueue.offer(msg, 1, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " producer rejected, retry: " + retries++);
                    Thread.sleep(new Random().nextInt(3000));
                }
                System.out.println(Thread.currentThread().getName() + " - produced: " + msg + "(total: " + retries + ", "+blockingQueue.size()+")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Consumer implements Runnable {

        private final BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                int retries = 0;
                String msg = blockingQueue.poll(1, TimeUnit.MILLISECONDS);
                while (msg == null) {
                    System.out.println(Thread.currentThread().getName() + "consumer rejected, retry: " + retries++);
                    Thread.sleep(new Random().nextInt(3000));
                    msg = blockingQueue.poll(1, TimeUnit.MICROSECONDS);
                }
                System.out.println(Thread.currentThread().getName() + " - consumed: " + msg + "(total: " + retries + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);

        ExecutorService producerExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < NUM_OF_MSGS; i++) {
            producerExecutor.execute(new Producer(blockingQueue, "MSG" + i));
        }
        producerExecutor.shutdown();

        ExecutorService consumerExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < NUM_OF_MSGS; i++) {
            consumerExecutor.execute(new Consumer(blockingQueue));
        }
        consumerExecutor.shutdown();

    }

}
