package by.bsu.ivanyukovich;

import com.sun.swing.internal.plaf.metal.resources.metal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hope on 4/3/14.
 */
public class Coder {
    private static final String SYMBOL = "([\\w ,\\.'?!])";
    public String text;

    public Coder(String text) {
        this.text = text;
    }

    public String encrypt(){
        Pattern pattern = Pattern.compile(SYMBOL + "+");
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()){
            pattern = Pattern.compile(SYMBOL + SYMBOL + "?" + SYMBOL + "?");
            matcher = pattern.matcher(text);
            String group1 = "";
            String group2 = "";
            String group3 = "";
            while (matcher.find()){
                group1 += matcher.group(1);
                group2 += matcher.group(2) == null ? "" : matcher.group(2);
                group3 += matcher.group(3) == null ? "" : matcher.group(3);
            }
            return group1 + group2 + group3;
        }
        return "Incorrect data.";
    }

}
