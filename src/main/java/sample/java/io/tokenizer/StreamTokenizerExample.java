package sample.java.io.tokenizer;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class StreamTokenizerExample {
    public static void main(String[] args) {
        String msg = "Once upon a time, 50 years ago...";

        try (StringReader reader = new StringReader(msg)) {
            StreamTokenizer tokenizer = new StreamTokenizer(reader);
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                    System.out.println(tokenizer.sval);
                } else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println(tokenizer.nval);
                } else if (tokenizer.ttype == StreamTokenizer.TT_EOL) {
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
