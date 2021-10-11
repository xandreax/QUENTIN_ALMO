package exceptions;

public class VictoryException extends Throwable{
    //FIELDS
    protected final String message;

    //CONSTRUCTORS
    public VictoryException() {
        this.message = "Victory!";
    }

    public VictoryException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }
}
