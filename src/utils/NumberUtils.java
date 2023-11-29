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
    public static int tryParseInt(String value) {
        int result = -1;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {}

        return result;
    }
}
