package sample.java.concurrency.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class CounterRunnable implements Runnable {

    private AtomicInteger counter = null;

    public CounterRunnable(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Started");
        for (int i = 0; i < 100; i++) {
            counter.getAndIncrement();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Done");
    }

}
