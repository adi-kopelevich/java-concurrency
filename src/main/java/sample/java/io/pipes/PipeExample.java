package sample.java.io.pipes;

import java.io.*;

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

        final PipedWriter writer = new PipedWriter();
        final PipedReader reader = new PipedReader(writer);

        Thread writerThread2 = new Thread(new Runnable() {
            String msg2 = "It's Trategty!";

            @Override
            public void run() {
                try {
                    writer.write(msg2);
                    System.out.println(Thread.currentThread().getName() + ": written - " + msg2);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread readerThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    char[] buffer = new char[16];
                    int currentChar = reader.read(buffer);
                    while (currentChar != -1) {
                        System.out.println(Thread.currentThread().getName() + ": read chunk -" + new String(buffer, 0, currentChar));
                        currentChar = reader.read(buffer);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        readerThread2.start();
        writerThread2.start();

    }
}
