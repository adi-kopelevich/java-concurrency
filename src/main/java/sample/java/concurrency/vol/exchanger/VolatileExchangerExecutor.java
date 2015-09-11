package sample.java.concurrency.vol.exchanger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread A may be putting objects from time to time by calling put(). Thread B may take objects from time to time by calling take().
 * This Exchanger can work just fine using a volatile variable (without the use of synchronized blocks), as long as only Thread A calls put() and only Thread B calls take().
 */
public class VolatileExchangerExecutor {

    public static void main(String[] args) {
        VolatileExchanger exchanger = new VolatileExchanger();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        executor.execute(new Runnable() {
                             public void run() {
                                 for (int i = 0; i < 10; i++) {
                                     exchanger.put("Str" + i);
                                 }
                             }
                         }
        );

        executor.execute(new Runnable() {
                             public void run() {
                                 for (int i = 0; i < 10; i++) {
                                     exchanger.take();
                                 }
                             }
                         }
        );

        executor.shutdown();
    }
}
