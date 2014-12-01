package alpacafarmerserver;

/**
 * Created by ndh13 on 29/11/14.
 */
public class Util {

    public static boolean isAlphaNumeric(String s) {
        return s.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isSuitableLength(String s) {
        return (s.length() > 2 && s.length() < 16);
    }
}
