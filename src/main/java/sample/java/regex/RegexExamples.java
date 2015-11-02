package sample.java.regex;

import java.util.regex.Pattern;

/**
 * Created by kopelevi on 02/11/2015.
 */
public class RegexExamples {

    public static void main(String[] args) {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = ".*[Jj]ohn.*";
        System.out.println(Pattern.matches(patternString1,text));
    }
}
