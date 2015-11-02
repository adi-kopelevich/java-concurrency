package sample.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopelevi on 02/11/2015.
 */
public class RegexExample {
    public static void main(String[] args) {
        String text =
                "This is the text to be searched " +
                        "for occurrences of the http:// pattern.";
        System.out.println(text);

        String patternMatch = ".*http://.*";
        System.out.println("matches = " + Pattern.matches(patternMatch, text));

        String patternUnMatch = "http://";
        System.out.println("matches = " + Pattern.matches(patternUnMatch, text));


        String text2 =
                "This is the text which IS to be searched " +
                        "for occurrences of the word 'is'.";
        System.out.println(text2);

        String patternString = "is";
        System.out.println(patternString);
        Pattern pattern2 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern2.matcher(text2);
        int numOfApp = 0;
        while (matcher.find()) {
            numOfApp++;
            System.out.println(numOfApp + ". Start: " + matcher.start() + ", End: " + matcher.end()+", Group: " +matcher.group());

        }

        String patternAllString = ".*is.*";
        System.out.println(patternAllString);
        Pattern pattern3 = Pattern.compile(patternAllString, Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern3.matcher(text2);
        int numOfApp2 = 0;
        while (matcher2.find()) {
            numOfApp2++;
            System.out.println(numOfApp2 + ". Start: " + matcher2.start() + ", End: " + matcher2.end()+", Group: " +matcher2.group());
        }
    }
}
