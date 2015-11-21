package sample.java.concurrency.timeout.task;

import java.util.concurrent.*;

/**
 * Created by kopelevi on 16/09/2015.
 */
public class TimeoutTaskExecutor {

    public static void main(String[] args) {

        final int TIME_LIMIT_IN_SEC = 2;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((TIME_LIMIT_IN_SEC - 1) * 1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted");
                }
            }
        });
        try {
            Object retObj = future.get(TIME_LIMIT_IN_SEC, TimeUnit.SECONDS);
            System.out.println("Runnable Task completed, return value is " + retObj);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Exceeded time limit, going to cancel task...");
            future.cancel(true);    // cancel a specific thread on the count of exceeding the time limit
//            executor.shutdownNow();  // shutting down all tasks executed by this executor
        }

//        try {
//            Thread.sleep((TIME_LIMIT_IN_SEC + 2) * 1000); // making sure the task is done
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String finalMsg = future.isDone() ? "Task was done" : "Task didn't complete yet...";
//        System.out.println(finalMsg);
        executor.shutdown();

        ExecutorService callableExecutor = Executors.newSingleThreadExecutor();
        Future<String> callableFuture = callableExecutor.submit(new Callable<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep((TIME_LIMIT_IN_SEC - 1) * 1000);
                } catch (InterruptedException e) {
                    String message = Thread.currentThread().getName() + " was interrupted";
                    System.out.println(message);
                    throw new RuntimeException(message);
                }
                return "Hi";
            }
        });

        String callableReturnValue = null;
        try {
            callableReturnValue = callableFuture.get(TIME_LIMIT_IN_SEC, TimeUnit.SECONDS);
            System.out.println("Callable task completed, return object is " + callableReturnValue);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Exceeded time limit, going to cancel task...");
            callableFuture.cancel(true);
        }

        callableExecutor.shutdown();


    }
}
