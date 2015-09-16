package sample.java.concurrency.thread.signaling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kopelevi on 12/09/2015.
 */
public class MyWaitNotifyExample {

    public static void main(String[] args) {

        MyWaitNotify watNotify = new MyWaitNotify();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    watNotify.doWait();
                }
            });
        }

        executor.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                watNotify.doNotify();
            }
        });
        executor.shutdown();
    }
}
