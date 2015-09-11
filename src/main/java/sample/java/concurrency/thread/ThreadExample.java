package sample.java.concurrency.thread;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class ThreadExample {

    public static void main(String[] args) {
        System.out.println("Main: " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("Thread-" + i) {
                public void run() {
                    System.out.println("Thread " + getName() + " is running...");
                }
            }.start();

        }
    }

}
