package sample.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopelevi on 02/11/2015.
 */
public class MatcherGroupExample {

    public static void main(String[] args) {

        String text =
                "John writes about this, and John Doe writes about that," +
                        " and John Wayne writes about everything.";

        String patternString1 = "(John) (.+?) ";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: "
                    + matcher.group(1) +
                    " " + matcher.group(2));
        }

        String text2    =
                "John writes about this, and John Doe writes about that," +
                        " and John Wayne writes about everything."
                ;

        String patternString2 = "((John) (.+?)) ";

        Pattern pattern2 = Pattern.compile(patternString2);
        Matcher matcher2 = pattern2.matcher(text);

        while(matcher2.find()) {
            System.out.println("found: <"  + matcher2.group(1) +
                    "> <"       + matcher2.group(2) +
                    "> <"       + matcher2.group(3) + ">");
        }
    }
}
