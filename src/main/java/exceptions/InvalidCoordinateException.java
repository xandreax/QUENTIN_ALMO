package exceptions;

public class InvalidCoordinateException extends Exception {

    //CONSTRUCTORS
    public InvalidCoordinateException() {
        super("The inserted coordinate is not valid, please use a valid one");
    }

    public InvalidCoordinateException(String message) {
        super(message);
    }
}
