package exceptions;

public class IllegalMoveException extends Throwable{
    //FIELDS
    protected final String message;

    //CONSTRUCTORS
    public IllegalMoveException() {
        this.message = "Generic IllegalMoveException";
    }

    public IllegalMoveException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }
}
