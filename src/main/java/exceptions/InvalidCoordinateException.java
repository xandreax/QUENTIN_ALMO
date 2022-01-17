package exceptions;

public class InvalidCoordinateException extends Exception {
    //FIELDS
    private final String message;

    //CONSTRUCTORS
    public InvalidCoordinateException() {
        this.message = "The inserted coordinate is not valid, please use a valid one";
    }

    public InvalidCoordinateException(String message) {
        this.message = message;
    }
}
