package utils;

public class IntUtils {
    //METHODS
    public static char mapInputIndexToInputChar(int n) {
        if (n == 0) return 'A';
        else if (n == 1) return 'B';
        else if (n == 2) return 'C';
        else if (n == 3) return 'D';
        else if (n == 4) return 'E';
        else if (n == 5) return 'F';
        else if (n == 6) return 'G';
        else if (n == 7) return 'H';
        else if (n == 8) return 'I';
        else if (n == 9) return 'J';
        else if (n == 10) return 'K';
        else if (n == 11) return 'L';
        else if (n == 12) return 'M';
        else throw new IndexOutOfBoundsException();
    }

    public static boolean isValidIntForCoordinate(int index) {
        return (index >= 0 && index <= 12);
    }
}
