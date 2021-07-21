package utils;

public class StringUtils {
    //METHODS
    public static boolean is2LiteralsValidCoordinate(String string) {
        return string.matches("[(a-m)(A-M)(0-9)]{2}");
    }

    public static boolean is3LiteralsValidCoordinate(String string) {
        return string.matches("[(a-m)(A-M)(|1[0-2])]{3}");
    }
}
