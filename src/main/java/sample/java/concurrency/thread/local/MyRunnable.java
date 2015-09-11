package sample.java.concurrency.thread.local;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class MyRunnable implements Runnable {

    ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "ThreadLocalInitValue";
        }
    };

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        threadLocal.set("Hello From " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
    }
}
