package utils;

public class CharUtils {
    //METHODS
    public static boolean isValidCharForCoordinate(char c) {
        return String.valueOf(c).matches("[a-mA-M0-12]{1}");
    }

    public static int mapInputCharToInputInt(char c) {
        if ((c == 'a') || (c == 'A')) return 0;
        else if ((c == 'b') || (c == 'B')) return 1;
        else if ((c == 'c') || (c == 'C')) return 2;
        else if ((c == 'd') || (c == 'D')) return 3;
        else if ((c == 'e') || (c == 'E')) return 4;
        else if ((c == 'f') || (c == 'F')) return 5;
        else if ((c == 'g') || (c == 'G')) return 6;
        else if ((c == 'h') || (c == 'H')) return 7;
        else if ((c == 'i') || (c == 'I')) return 8;
        else if ((c == 'j') || (c == 'J')) return 9;
        else if ((c == 'k') || (c == 'K')) return 10;
        else if ((c == 'l') || (c == 'L')) return 11;
        else if ((c == 'm') || (c == 'M')) return 12;
        else throw new IndexOutOfBoundsException();
    }
}
