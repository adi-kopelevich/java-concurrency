package sample.java.io;

import java.io.*;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class DataInputOutputStreamExample {

    public static void main(String[] args) {
        String filename = "C:\\hi.txt";


        try (OutputStream outputStream = new FileOutputStream(filename);
             DataOutputStream dataOutput = new DataOutputStream(outputStream)) {
            dataOutput.writeBoolean(true);
            dataOutput.writeFloat(123.45F);
            dataOutput.writeInt(789);
            dataOutput.writeLong(789);
            dataOutput.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream(filename);
             DataInputStream dataInput = new DataInputStream(inputStream)) {
            System.out.println(dataInput.readBoolean());
            System.out.println(dataInput.readFloat());
            System.out.println(dataInput.readInt());
            System.out.println(dataInput.readLong());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
