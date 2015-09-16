package sample.java.concurrency.wait.notify;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class Producer implements Runnable {

    private LinkedList<Integer> list;
    private final int limit;

    public Producer(LinkedList<Integer> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @Override
    public void run() {

        synchronized (list) {
            try {
                while (list.size() == limit) {
                    System.out.println(Thread.currentThread().getName() + ": hit upper limit,  wait for consumers to take an item from the list");
                    list.wait();
                }

                System.out.println(Thread.currentThread().getName() + ": List size is " + list.size());
                Thread.sleep(new Random().nextInt(3000));
                int val = new Random().nextInt(10);
                list.add(val);
                System.out.println(Thread.currentThread().getName() + ": Added value is " + val);
                list.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
