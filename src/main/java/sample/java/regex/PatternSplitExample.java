package sample.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kopelevi on 02/11/2015.
 */
public class PatternSplitExample {

    public static void main(String[] args) {

        String text = "A sepText sepWith sepMany sepSeparators";
        System.out.println(text);
        String patternString = "sep";

        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        String[] strArr = pattern.split(text);
        for(String str : strArr){
            System.out.println(str);
        }
        System.out.println("Pattern Text: "+ pattern.pattern());

        String headStr = "A sepText";
        System.out.println("headStr: "+headStr);
        Pattern headerPAttern = Pattern.compile(headStr);
        Matcher matcher = headerPAttern.matcher(text);
        System.out.println("matcher.lookingAt() - "+matcher.lookingAt());
        System.out.println("matcher.matches() - "+matcher.matches());

    }
}
