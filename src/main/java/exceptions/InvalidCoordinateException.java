package exceptions;

public class InvalidCoordinateException extends Throwable {
    //FIELDS
    protected final String message;

    //CONSTRUCTORS
    public InvalidCoordinateException() {
        this.message = "Generic InvalidCoordinateException";
    }

    public InvalidCoordinateException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }
}
