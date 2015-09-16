package sample.java.concurrency.vol.exchanger;

/**
 * Created by kopelevi on 11/09/2015.
 */
public class VolatileExchanger {

    private Object obj = null;
    private volatile boolean isUpdated = false;

    public void put(Object obj) {
        while (isUpdated) { // volatile read
            //wait - do not overwrite existing new object (Busy waiting)
        }
        this.obj = obj;
        isUpdated = true; // volatile write
        System.out.println(Thread.currentThread().getName() + ": Put: " + obj);

    }

    public Object take() {
        while (!isUpdated) { // volatile read
            // wait - do not take old object (or null)
        }
        Object o = obj;
        isUpdated = false; // volatile write
        System.out.println(Thread.currentThread().getName() + ": Took: " + o);
        return o;
    }
}
