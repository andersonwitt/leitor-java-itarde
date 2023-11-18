package utils;

public class NumberUtils {
    public static boolean IsNumber(String value) {
        boolean result = false;
        try {
            Integer.parseInt(value);
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }

        return result;
    }
}
