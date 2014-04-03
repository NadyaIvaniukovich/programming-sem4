package by.bsu.ivanyukovich;

/**
 * Created by Hope on 4/3/14.
 */

public class Main {
    private final static String TEXT = "My name is Freddy. I'm 14. How are you? Sorry, I don't understand you.";
    public static void main(String[] args) {
        Coder coder = new Coder(TEXT);
        System.out.println(coder.encrypt());
    }
}
