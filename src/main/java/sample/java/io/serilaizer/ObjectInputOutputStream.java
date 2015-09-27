package sample.java.io.serilaizer;

import java.io.*;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class ObjectInputOutputStream {

    public static void main(String[] args) {
        String filename = "c:\\hi.txt";
        try (OutputStream outputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream)){
            Person p = new Person("haim", "moshe");
            objectOutput.writeObject(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream(filename);
             ObjectInputStream objectOutput = new ObjectInputStream(inputStream)){
            Person p = (Person) objectOutput.readObject();
            System.out.println(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
