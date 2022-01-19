package exceptions;

public class IllegalMoveException extends Exception{
    //FIELDS
    private final String message;

    public IllegalMoveException(String message) {
        this.message = message;
    }

}
