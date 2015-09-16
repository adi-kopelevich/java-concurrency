package sample.java.concurrency.thread.signaling;

/**
 * Created by kopelevi on 12/09/2015.
 */
public class MyWaitNotify {  // to avoid busy waiting

    private Integer myMonitoringObj = new Integer(1); // avoid using strings since the JVM stores only 1 instance of them and they could be shared between multi wait-notify
    private boolean wasSingled = false;  // to make sure waiting thread are not waiting forever on a singal that already happendd,

    public void doWait(){
        synchronized (myMonitoringObj){
            while(!wasSingled){  //spin lock - while loop and not if statement in order to handle spurious thread wakeups
                System.out.println(Thread.currentThread().getName() + ": waiting...");
                try {
                    myMonitoringObj.wait();
                    System.out.println(Thread.currentThread().getName() + ": waken...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + ": done job...");
            // clear signal and continue running
            wasSingled = false;
        }
    }

    public void doNotify(){
        synchronized (myMonitoringObj){
            wasSingled = true;
//            myMonitoringObj.notify();
//            System.out.println(Thread.currentThread().getName() + ": notify single...");
            myMonitoringObj.notifyAll();
            System.out.println(Thread.currentThread().getName() + ": notify all...");
        }
    }
}
