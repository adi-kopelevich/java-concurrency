package sample.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by kopelevi on 30/09/2015.
 */
public class ChannelToChannelTransferExample {

    public static void main(String[] args) {
        try (RandomAccessFile fromFile = new RandomAccessFile("c:\\hi.txt", "rw");
             FileChannel fromChannel = fromFile.getChannel();
             RandomAccessFile toFile = new RandomAccessFile("c:\\hi2.txt", "rw");
             FileChannel toChannel = toFile.getChannel()) {

            long pos = 0;
            long count = fromChannel.size();

            fromChannel.transferTo(pos, count, toChannel);
            toChannel.transferFrom(fromChannel, pos, count); //equals to the above line command

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
