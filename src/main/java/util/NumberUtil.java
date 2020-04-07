package util;

public class NumberUtil {

    public static boolean isNumber(String str) {
        for(int i = 0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

}
