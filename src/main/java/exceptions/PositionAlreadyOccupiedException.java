package exceptions;

public class PositionAlreadyOccupiedException extends Throwable{
    //FIELDS
    protected final String message;

    //CONSTRUCTORS
    public PositionAlreadyOccupiedException() {
        this.message = "This position is already occupied by another piece";
    }

    public PositionAlreadyOccupiedException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }
}
