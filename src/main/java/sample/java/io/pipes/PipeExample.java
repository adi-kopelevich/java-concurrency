package sample.java.io.pipes;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class PipeExample {

    public static void main(String[] args) throws IOException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);

        Thread writerThread = new Thread(new Runnable() {
            String msg = "Hi Mate! Trategy when you lose control and you got no sole";

            @Override
            public void run() {
                try {
                    output.write(msg.getBytes());
                    System.out.println(Thread.currentThread().getName() + ": written - " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] buffer = new byte[16];
                    int currentChar = input.read(buffer);
                    while (currentChar != -1) {
                        System.out.println(Thread.currentThread().getName() + ": read chunk -" + new String(buffer, 0, currentChar));
                        currentChar = input.read(buffer);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        readerThread.start();
        writerThread.start();

    }
}
