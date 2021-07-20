package utils;

public class StringUtils {
    //METHODS
    public static boolean isCoordinate(String string) {
        return string.matches("[a-mA-M([0-9] | [0-1][0-2])]{2}");
    }
}
