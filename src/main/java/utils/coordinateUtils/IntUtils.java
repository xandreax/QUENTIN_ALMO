package utils.coordinateUtils;

public class IntUtils {
    //METHODS
    public static String mapLegendChar(int n) {
        if (n == 0) return "A";
        else if (n == 2) return "B";
        else if (n == 4) return "C";
        else if (n == 6) return "D";
        else if (n == 8) return "E";
        else if (n == 10) return "F";
        else if (n == 12) return "G";
        else if (n == 14) return "H";
        else if (n == 16) return "I";
        else if (n == 18) return "J";
        else if (n == 20) return "K";
        else if (n == 22) return "L";
        else if (n == 24) return "M";
        else return " ";
    }

    public static String mapBorderChar(int n) {
        if (n == 6) return "W";
        else if (n == 7) return "H";
        else if (n == 8) return "I";
        else if (n == 9) return "T";
        else if (n == 10) return "E";
        else if (n == 14) return "L";
        else if (n == 15) return "I";
        else if (n == 16) return "N";
        else if (n == 17) return "E";
        else return "|";
    }

    public static boolean isValidIntForBoardCoordinate(int index) {
        return (index >= 0 && index <= 12);
    }
    public static boolean isValidIntForGUICoordinate(int index) {
        return (index >= 0);
    }
}
