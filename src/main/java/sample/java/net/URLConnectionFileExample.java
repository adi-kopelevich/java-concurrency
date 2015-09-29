package sample.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kopelevi on 29/09/2015.
 */
public class URLConnectionFileExample {

    public static void main(String[] args) {
        try (InputStream inputStream = getConnection().getInputStream()) {
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                System.out.println("Read: " + (char) currentByte);
                currentByte = inputStream.read();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static URLConnection getConnection() {
//        String filePath = "file:/c:/hi.txt";
        String filePath = "http://www.google.com";

        URLConnection urlConnection = null;
        try {
            URL url = new URL(filePath);
            urlConnection = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlConnection;
    }
}
