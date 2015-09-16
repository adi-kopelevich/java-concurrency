package sample.java.concurrency.sample.java.concurrency.producer.consume.wait.notify;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class Consumer implements Runnable {

    private LinkedList<Integer> list;
    private final int limit;

    public Consumer(LinkedList<Integer> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @Override
    public void run() {
        synchronized (list) {
            try {
                while (list.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + ": hit lower limit,  wait for producer to put an item in the list");
                    list.wait();
                }
                System.out.println(Thread.currentThread().getName() + ": List size is " + list.size());
                int val = list.removeFirst();
                Thread.sleep(new Random().nextInt(3000));
                System.out.println(Thread.currentThread().getName() + ": Consumed value is " + val);
                list.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
