package exceptions;

public class PositionAlreadyOccupiedException extends Exception{
    //FIELDS
    private final String message;

    //CONSTRUCTORS
    public PositionAlreadyOccupiedException() {
        this.message = "This position is already occupied by another piece";
    }

    public PositionAlreadyOccupiedException(String message) {
        this.message = message;
    }
}
