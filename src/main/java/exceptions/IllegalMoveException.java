package exceptions;

public class IllegalMoveException extends Throwable{
    //FIELDS
    protected final String message;

    //CONSTRUCTORS
    public IllegalMoveException() {
        this.message = "This move is not valid, please try again with a valid one";
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
