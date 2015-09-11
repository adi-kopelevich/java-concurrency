package sample.java.concurrency.counter;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class CounterRunnable implements Runnable {

    private Counter counter = null;

    public CounterRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Started");
        for (int i = 0; i < 10; i++) {
            counter.add();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Done");
    }

}
