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
            String msg = "Hi Mate!";

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
                    int currentChar = input.read();
                    while (currentChar != -1) {
                        System.out.println(Thread.currentThread().getName() + ": read -" + String.valueOf((char) currentChar));
                        currentChar = input.read();
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
