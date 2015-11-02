package sample.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopelevi on 02/11/2015.
 */
public class MatcherReplaceExample {

    public static void main(String[] args) {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "((John) (.+?)) ";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        String replaceAll = matcher.replaceAll("John Rules ");
        System.out.println("Replace All: " +replaceAll);
        String replaceFirst = matcher.replaceFirst("John Rules ");
        System.out.println("Replace First: " +replaceFirst);

        String text2 = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString2 = "((John) (.+?)) ";
        Pattern pattern2 = Pattern.compile(patternString1);
        Matcher matcher2 = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        while(matcher2.find()){
            matcher2.appendReplacement(stringBuffer, "Joe Blocks ");
            System.out.println(stringBuffer.toString());
        }
        matcher2.appendTail(stringBuffer);

        System.out.println(stringBuffer.toString());

    }
}
