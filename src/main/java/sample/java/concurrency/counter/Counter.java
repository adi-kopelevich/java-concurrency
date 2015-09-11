package sample.java.concurrency.counter;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class Counter {
    private int counter = 0;

    public synchronized void add() {
        counter = counter + 1;
        System.out.println(Thread.currentThread().getName() + ": " + counter);
    }

    public String toString() {
        return String.valueOf(counter);
    }

}
