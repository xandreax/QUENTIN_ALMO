package exceptions;

public class UnsupportedPiecesForPlayerException extends Throwable{
    //FIELDS
    protected String message;

    //CONSTRUCTORS
    public UnsupportedPiecesForPlayerException() {
        this.message = "Generic UnsupportedPiecesForPlayerException";
    }

    public UnsupportedPiecesForPlayerException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
