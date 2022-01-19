package exceptions;

public class PositionAlreadyOccupiedException extends Exception{
    //FIELDS
    private final String message;

    public PositionAlreadyOccupiedException(String message) {
        this.message = message;
    }
}
